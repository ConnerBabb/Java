package com.techelevator;

import com.techelevator.view.Menu;

public class AddressBookCLI {

	private Menu menu;
	
	public AddressBookCLI(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
		
		menu.printMessage("Welcome to the Address Book");
		
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		AddressBookCLI addressBookCLI = new AddressBookCLI(menu);
		addressBookCLI.run();
	}

}
