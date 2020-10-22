package com.techelevator;

import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class SameFirstLastTest {
	private SameFirstLast sameFirstLast;
	
	@Before
	public void setup() {
		sameFirstLast = new SameFirstLast();
	}
	@Test
	public void not_the_same() {
		int[] nums = {1,1,2,1,3};
		boolean result = sameFirstLast.isItTheSame(nums);
		Assert.assertFalse(result);
	}
	@Test
	public void is_the_same() {
		int[] nums = {3,1,2,1,3};
		boolean result = sameFirstLast.isItTheSame(nums);
		Assert.assertTrue(result);
	}
	@Test
	public void is_empty() {
		int[] nums = {};
		boolean result = sameFirstLast.isItTheSame(nums);
		Assert.assertFalse(result);
	}
}
