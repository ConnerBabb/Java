package com.techelevator;

public class SavingsAccount extends BankAccount {

	public SavingsAccount(String accountHolderName, String accountNumber, int balance) {
		super(accountHolderName, accountNumber, balance);
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(String accountHolderName, String accountNumber) {
		super(accountHolderName, accountNumber);
		// TODO Auto-generated constructor stub
	}
	public int withdraw(int amountToWithdraw){
		if (getBalance()-amountToWithdraw<150 && getBalance()-amountToWithdraw > 0) {
			super.withdraw(amountToWithdraw +2);
			return getBalance();
		}
		if (getBalance() - amountToWithdraw < 0) {
			return getBalance();
		}
		super.withdraw(amountToWithdraw);
		return getBalance();
	}
	
}
