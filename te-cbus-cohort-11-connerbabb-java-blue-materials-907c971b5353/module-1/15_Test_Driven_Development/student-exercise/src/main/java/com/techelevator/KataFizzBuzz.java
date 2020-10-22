package com.techelevator;

public class KataFizzBuzz {
	public static String fizzBuzz(int start) {
		if( start <1 || start > 100) {
			return "";
		}
		if((start % 3 == 0 && start % 5 == 0) || (Integer.toString(start).contains("5") && Integer.toString(start).contains("3"))) {
			return "FizzBuzz";
		}
		if(start % 5 == 0||Integer.toString(start).contains("5") ) {
			return "Buzz";
		}
		if(start % 3 == 0 || Integer.toString(start).contains("3")) {
			return "Fizz";
		}
		if( start >=1 && start <= 100) {
			return Integer.toString(start);
		} return null;
	} 
	

}
