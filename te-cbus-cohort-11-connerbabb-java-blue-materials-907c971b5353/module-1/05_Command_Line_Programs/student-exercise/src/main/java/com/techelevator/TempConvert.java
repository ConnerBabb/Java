package com.techelevator;

import java.util.Scanner;

public class TempConvert {

	public static void main(String[] args) {
		System.out.print("Please enter a temperature >>>");
		
		Scanner in = new Scanner(System.in);
		
		String userInputTemp = in.nextLine();
		
		System.out.print("Is this temperature in fahrenheit(1) or celcius(2)?>>>");
		
		String userInput = in.nextLine();
		
		Double convertedTemp = 0.0;
		
		
		if (Integer.parseInt(userInput )== 1) {
			convertedTemp = (Double.parseDouble(userInputTemp) - 32.0) / 1.8;
			System.out.println(userInputTemp + " degrees fahrenheit is " + convertedTemp + " degrees celcius." );
		} else if (Integer.parseInt(userInput )== 2) {
			convertedTemp = Double.parseDouble(userInputTemp) * 1.8 + 32;
			System.out.println(userInputTemp + " degrees celcius is " + convertedTemp + " degrees fahrenheit." );
		}
	}

}
