package com.techelevator;

import java.util.Scanner;

public class LinearConvert {

	public static void main(String[] args) {
		System.out.print("Please enter the length >>>");
		
		Scanner in = new Scanner(System.in);
		
		String userInputLength = in.nextLine();
		
		System.out.print("Is this measurement in meter(1) or feet(2)? >>>");
		
		String userInput = in.nextLine();
		
		Double convertedLength = 0.0;
		
		
		if (Integer.parseInt(userInput )== 1) {
			convertedLength = (Double.parseDouble(userInputLength)) *  3.2808399;
			System.out.println(userInputLength + "m is " + convertedLength + "f" );
		} else if (Integer.parseInt(userInput )== 2) {
			convertedLength = Double.parseDouble(userInputLength) * .3048;
			System.out.println(userInputLength + "f is " + convertedLength + "m" );
		}
	}

}
