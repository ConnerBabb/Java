package com.techelevator;

import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class Lucky13Test {
	private Lucky13 lucky13;
	
	@Before
	public void setup() {
		lucky13 = new Lucky13();
	}
	
	@Test
	public void contains_1() {
		int[] nums = {1,1,5};
		boolean result = lucky13.getLucky(nums);
		Assert.assertFalse(result);
	}
	@Test
	public void contains_3() {
		int[] nums = {4,3,5};
		boolean result = lucky13.getLucky(nums);
		Assert.assertFalse(result);
	}
	@Test
	public void is_lucky() {
		int[] nums = {4,10,5};
		boolean result = lucky13.getLucky(nums);
		Assert.assertTrue(result);
	}

}
