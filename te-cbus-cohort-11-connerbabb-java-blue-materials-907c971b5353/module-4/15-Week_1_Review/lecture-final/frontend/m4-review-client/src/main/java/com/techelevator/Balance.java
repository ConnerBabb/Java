package com.techelevator;

import java.io.Serializable;

public class Balance implements Serializable {

	private static final long serialVersionUID = 1556579118996563664L;
	
	private int accountId;
	private double amount;
	
	public Balance() {
		
	}
	
	public Balance(int accountId, double amount) {
		super();
		this.accountId = accountId;
		this.amount = amount;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
}
