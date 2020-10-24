package com.techelevator;

import java.util.Scanner;

public class CliExample {

	public static void main(String[] args) {
		
		Scanner input = new Scanner( System.in );
		
		System.out.println("F or C");
		
		String userInput = input.nextLine();
		
		//if (userInput.toUpperCase().equals("F"))
		// if (userInput.equals("F"))
		
		if (userInput.equalsIgnoreCase("F")) {
			System.out.println("You choose F");
		} else if (userInput.equalsIgnoreCase("C")) {
			System.out.println("You choose C");
		} else {
			System.out.println("Please choose F or C");
		}

	}

}
