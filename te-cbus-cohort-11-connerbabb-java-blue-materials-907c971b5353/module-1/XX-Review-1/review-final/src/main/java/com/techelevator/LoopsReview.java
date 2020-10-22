package com.techelevator;

public class LoopsReview {

	public static void main(String[] args) {
		
		for ( int i = 0 ; i < 10 ; i++ ) {
			System.out.println(i);
		}
		
		
		String[] stringArray = { "A", "B", "C", "D" };
		
		for ( int i = 0 ; i < stringArray.length ; i++) {
			
			if ( i < stringArray.length - 1 &&
					stringArray[i].equals("B") ) {
				i++;
			}
			
			System.out.println( stringArray[ i ] );
		}
		
	}
	
}
