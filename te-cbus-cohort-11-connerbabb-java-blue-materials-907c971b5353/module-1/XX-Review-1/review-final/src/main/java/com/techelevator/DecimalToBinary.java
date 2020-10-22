package com.techelevator;

import java.util.Scanner;

public class DecimalToBinary {

	public static void main(String[] args) {
		
		// 12 512 33
		
		Scanner userInput = new Scanner( System.in );
		
		System.out.print("Enter decimal numbers >>>");
		
		String inputValue = userInput.nextLine();
		
		String[] decimalNumbers = inputValue.split(" ");
		
		for (int i = 0; i < decimalNumbers.length; i++) {
			
			int decimalNum = Integer.parseInt(decimalNumbers[i]);
			
			String binaryNumber = "";
			
			while ( decimalNum > 0) {
				binaryNumber += String.valueOf(decimalNum % 2);
				decimalNum = decimalNum / 2;
			}
			
			System.out.println(binaryNumber);
		}
	}

}
