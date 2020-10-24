package com.techelevator;

public class LoopExamples {

	public static void main(String[] args) {
		
		for (int i = 0 ; i < 10 ; i++) {
			System.out.println(i);
		}
		
		int sum = 0;
		for (int i=1 ; i <= 100; i++) {
			sum += i;
		}
		System.out.println( sum );
		
		sum = 0;
		for (int i = 2020; i <=8576; i++) {
			if (i % 2 == 1) {
			sum += i;
			}
		
		}
		System.out.println(sum);
	}
}
