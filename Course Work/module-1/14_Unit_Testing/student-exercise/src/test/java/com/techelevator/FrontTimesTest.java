package com.techelevator;

import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class FrontTimesTest {
	private FrontTimes frontTimes;
	
	@Before
	public void setup() {
		frontTimes = new FrontTimes();
	}
	@Test
	public void one_times() {
		String expectedResult = "Cho";
		String result = frontTimes.generateString("Chocolate",1);
		Assert.assertEquals(expectedResult,result);
	}
	@Test
	public void less_than_three() {
		String expectedResult = "ChChCh";
		String result = frontTimes.generateString("Ch",3);
		Assert.assertEquals(expectedResult,result);
	}
	@Test
	public void equals_three() {
		String expectedResult = "ChoCho";
		String result = frontTimes.generateString("Cho",2);
		Assert.assertEquals(expectedResult,result);
	}
}
