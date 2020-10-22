package com.techelevator.view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.techelevator.inventory.Stock;

public class Menu {

	private Scanner in = new Scanner(System.in);
	
	public String getUserInput(String question) {
		System.out.print(question);
		return in.nextLine();
	}
	
	public void showErrorMessage(String message) {
		displayMessage(message, true);
	}
	
	public void showMessage(String message) {
		displayMessage(message, false);
	}
	

	public void displayStock(Map<String, Stock> stock) {
		
		System.out.println();
		
		System.out.printf("%-6s  %-25s  %-7s   %-10s%n", "Code", "Product", "Price", "Available");
		System.out.println("------------------------------------------------------");
		
		for (Entry<String, Stock> entry : stock.entrySet()) {
			
			System.out.printf("%-6s  %-25s  $%5.2f     %-10s%n", 
					entry.getKey(),
					entry.getValue().getItem().getName(),
					entry.getValue().getItem().getPrice(), 
					( entry.getValue().getQuantity() > 0 ? entry.getValue().getQuantity() : "SOLD OUT" )
			);
			
		}
		
		System.out.flush();
		
	}
	
	private void displayMessage(String message, boolean isError) {
		System.out.println();
		System.out.println( (isError ? "ERROR: " : "") + message);
		System.out.println();
	}
	

	
}
