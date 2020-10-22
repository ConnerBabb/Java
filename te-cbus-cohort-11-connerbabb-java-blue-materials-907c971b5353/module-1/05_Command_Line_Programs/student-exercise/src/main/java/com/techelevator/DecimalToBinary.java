package com.techelevator;
import java.util.Scanner;
public class DecimalToBinary {

	public static void main(String[] args) {
		System.out.print("Please enter in a series of decimal values (sperated by spaces):");
		
		Scanner in = new Scanner(System.in);
		
		String userInput = in.nextLine();
		
		String[] userInputArr = userInput.split("\\s+");
		for (int i=0; i<userInputArr.length; i++) {
		int decimalNumber = Integer.parseInt(userInputArr[i]);
			int j =0;
			for (j= 1 ; decimalNumber>1 ; j++) {
				 decimalNumber = decimalNumber / 2;
			}
			Integer[] array = new Integer[j];
			int decimalNumberNew = Integer.parseInt(userInputArr[i]);
			for (int k = 1; decimalNumberNew >=1 ; k++) {
				array[array.length-k] = decimalNumberNew % 2;
				decimalNumberNew /= 2;
			}
			String finalBinary = "";
			for(int u = 0 ; u < array.length ; u++) {
				finalBinary = finalBinary + Integer.toString(array[u]);
			}
		System.out.println(userInputArr[i] +" in binary is " + finalBinary);
		}
	}
}