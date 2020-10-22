package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExceptionsReview {

	public static void main(String[] args)  throws FileNotFoundException {
	
//		try {
		Scanner input = new Scanner(System.in);
		
		while (true) {
			System.out.println("file path >>>");
			String filePath = input.nextLine();
			
			filePath.concat("World");
			
			try {
				openFile(filePath);
				break;
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} 
		
		System.out.println("File was successful");
		
//		try {
//			

			
//			String s = "abc";
//			int x = Integer.parseInt(s);
//			
//			int y = 10 + x;
//		} catch (NumberFormatException e) {
//			System.out.println("String does not contain a number");
//			System.out.println(e.getMessage());
//		}

	}
	
	private static void openFile(String filePath) throws FileNotFoundException
	{
		
		File file = new File(filePath);
		Scanner fileScanner = new Scanner(file);
		//throw new NullPointerException();
	}

}
