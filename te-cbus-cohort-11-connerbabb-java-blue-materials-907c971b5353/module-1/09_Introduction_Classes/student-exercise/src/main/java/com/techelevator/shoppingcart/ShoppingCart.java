package com.techelevator.shoppingcart;

import java.text.DecimalFormat;

public class ShoppingCart {
	private int totalNumberOfItems = 0;
	private double totalAmountOwed = 0.0;
	
	public int getTotalNumberOfItems() {
		return totalNumberOfItems;
	}
	
	public double getTotalAmountOwed() {
		return totalAmountOwed;
	}
	
	DecimalFormat toTheFormat = new DecimalFormat("#0.00");
	
	public double getAveragePricePerItem() {
		Double temp = totalAmountOwed /totalNumberOfItems;
		if(temp.isNaN()) {
			return 0.00;
		}
		return totalAmountOwed /totalNumberOfItems;
	}
	
	public void addItems(int numberOfItems, double pricePerItem) {
		this.totalNumberOfItems += numberOfItems;
		this.totalAmountOwed = totalAmountOwed + (pricePerItem * numberOfItems);
	}

	public void empty() {
		totalNumberOfItems = 0;
		totalAmountOwed = 0.0;
	}
	//
	// Write code here
	//
	
}
