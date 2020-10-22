package com.techelevator;

import java.io.File;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.reader.CsvInventoryReader;
import com.techelevator.inventory.reader.InventoryReader;
import com.techelevator.inventory.reader.InventoryReaderException;
import com.techelevator.inventory.reader.JdbcInventoryReader;
import com.techelevator.view.Menu;


public class InventoryDisplayCLI {

	private Menu menu;
	private Inventory inventory;
	
	public InventoryDisplayCLI(Menu menu, Inventory inventory) {
		this.menu = menu;
		this.inventory = inventory;
	}


	public void run() {	
		menu.displayStock( inventory.getStock()  );
	}
	

	
	public static void main(String[] args) throws InventoryReaderException {
		Menu menu = new Menu();
		
		InventoryReader reader = null;
		
		while (reader == null) {
			String userChoice = menu.getUserInput("Get Inventory from (F)ile or (D)atabase? >>>");
			
			if (userChoice.equalsIgnoreCase("F")) {
				
				reader = new CsvInventoryReader(getInventoryFile() );
			
			} else if (userChoice.equalsIgnoreCase("D")) {
			
				reader = new JdbcInventoryReader( getDataSource() );
			
			}  else {
				menu.showErrorMessage("Invalid Choice.. try again!");
			}
			
		}
		
		InventoryDisplayCLI cli = new InventoryDisplayCLI(menu, new Inventory(reader));
		cli.run();

	}
	
	
	private static File getInventoryFile() {
		String inventoryPath = "cateringsystem.csv";
		File inventoryFile = new File(inventoryPath);
		return inventoryFile;
	}
	
	
	private static DataSource getDataSource() {
		BasicDataSource cateringSystemDataSource = new BasicDataSource();
		cateringSystemDataSource.setUrl("jdbc:postgresql://localhost:5432/cateringsystem");
		cateringSystemDataSource.setUsername("postgres");
		cateringSystemDataSource.setPassword("postgres1");
		return cateringSystemDataSource;
	}
	
}
