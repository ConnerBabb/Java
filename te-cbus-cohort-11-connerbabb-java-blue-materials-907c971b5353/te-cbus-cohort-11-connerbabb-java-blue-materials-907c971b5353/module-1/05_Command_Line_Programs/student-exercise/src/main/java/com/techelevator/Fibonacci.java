package com.techelevator;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {
		
		int firstNum = 0;
		
		int secondNum = 1;
		
		System.out.println("Please enter the fibonacci number");
		
		Scanner in = new Scanner(System.in);
		
		String userInputNum = in.nextLine();
		
		int intUserInputNum = Integer.parseInt(userInputNum);
		
		int fibNum = 1;
		
		if (intUserInputNum == 0)
			System.out.println("0");
		
		if (intUserInputNum == 1)
			System.out.println("0, 1");
		
		for (int i = 0; i <= intUserInputNum; i = firstNum + secondNum) {
			if ((i + secondNum) > intUserInputNum) {
				System.out.print(i);
			} else System.out.print(i + ", ");
			firstNum = secondNum;
			secondNum = i;
			
		}

	}

}
