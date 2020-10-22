package com.techelevator;

import java.util.Scanner;

public class G {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("What is your number?>>");
		int userSelection = Integer.parseInt(userInput.nextLine());
		System.out.println(KataFizzBuzz.fizzBuzz(userSelection));
		

	}

}
