package com.techelevator;

import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class Less20Test {
	private Less20 less20;
	
	@Before
	public void setup() {
		less20 = new Less20();
	}
	@Test
	public void two_less() {
		boolean result = less20.isLessThanMultipleOf20(38);
		Assert.assertTrue(result);
	}
	@Test
	public void one_less() {
		boolean result = less20.isLessThanMultipleOf20(19);
		Assert.assertTrue(result);
	}
	@Test
	public void multipe_of_20() {
		boolean result = less20.isLessThanMultipleOf20(80);
		Assert.assertFalse(result);
	}
}
