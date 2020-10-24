package com.techelevator;

public class CheckingAccount extends BankAccount {

	public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
		super(accountHolderName, accountNumber, balance);
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(String accountHolderName, String accountNumber) {
		super(accountHolderName, accountNumber);
		// TODO Auto-generated constructor stub
	}
	
	public int withdraw(int amountToWithdraw) {
		if(getBalance() - amountToWithdraw < -100) {
			return getBalance();
		}
		if(amountToWithdraw > getBalance()) {
			super.withdraw(amountToWithdraw +10);
			return getBalance();
		}
		super.withdraw(amountToWithdraw);
		return getBalance();
	}
	


}
