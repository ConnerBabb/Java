package com.techelevator;

public class CreditCardAccount implements Accountable {

	private String accountHolder;
	private String accountNumber;
	private int debt;
	
	public CreditCardAccount(String accountHolder, String accountNumber) {
		debt = 0;
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
	}
	
	@Override
	public String toString() {
		return "CreditCardAccount [accountHolder=" + accountHolder + ", accountNumber=" + accountNumber + ", debt="
				+ debt + ", getAccountHolder()=" + getAccountHolder() + ", getAccountNumber()=" + getAccountNumber()
				+ ", getDebt()=" + getDebt() + ", getBalance()=" + getBalance() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public int getDebt() {
		return debt;
	}

	@Override
	public int getBalance() {
		// TODO Auto-generated method stub
		return 0 - debt;
	}
	
	public int pay(int amountToPay) {
		debt -= amountToPay;
		return debt;
	}
	
	public int charge(int amountToCharge) {
		debt += amountToCharge;
		return debt;
	}

}
