package com.techelevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCustomer{
	private String name;
	private String address;
	private String phoneNumber;
	private Accountable[] accounts;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Accountable[] getAccounts() {
		Accountable[] accounts2 = new Accountable[accountsList.size()];
		int i =0;
		for(Accountable a : accountsList) {
			accounts2[i] = a;
			i++;
		}

		return accounts2;
	
	}
	List<Accountable> accountsList = new ArrayList<Accountable>();
	
	public void addAccount(Accountable newAccount) {
	accountsList.add(newAccount);

	}
	
	public boolean isVip() {
		int balance = 0;
		for(int i = 0;i<getAccounts().length; i++) {
		balance += getAccounts()[i].getBalance();
		}
		if (balance < 25000) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public String toString() {
		return "BankCustomer [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", accounts="
				+ Arrays.toString(accounts) + ", accountsList=" + accountsList + ", getAccounts()="
				+ Arrays.toString(getAccounts()) + "]";
	}

}
