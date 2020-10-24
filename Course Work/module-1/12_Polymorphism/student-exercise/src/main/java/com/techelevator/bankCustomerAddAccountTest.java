package com.techelevator;


public class bankCustomerAddAccountTest {

	public static void main(String[] args) {
		CreditCardAccount connersCreditCard = new CreditCardAccount("conner", "10125");
		connersCreditCard.charge(100);
		CheckingAccount connersCheckingAccount = new CheckingAccount("conner", "10126", 25000);
		BankCustomer conner = new BankCustomer();
		
		conner.addAccount(connersCreditCard);
		conner.addAccount(connersCheckingAccount);
//		for(int i = 0;i<conner.getAccounts().length; i++) {
//		System.out.println(conner.getAccounts()[i].getBalance());
//		}
//		boolean test = true;
//		int balance = 0;
//		for(int i = 0;i<conner.getAccounts().length; i++) {
//		balance = conner.getAccounts()[i].getBalance();
//		}
//		if (balance < 25000) {
//			test = false;
//		} else {
//			test = true;
//		}
		Accountable[] test = conner.getAccounts();
		for(int i = 0 ; i < test.length; i++)
		
		System.out.println(conner.getAccounts()[i].toString());
		
		
		}

	}

