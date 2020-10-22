package com.techelevator;


import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class MaxEndTest {
	
	private MaxEnd3 maxEnd3;
	
	@Before
	public void setup() {
		maxEnd3 = new MaxEnd3();
	}
	
	@Test
	public void last_number_biggest() {
	int[] nums = {1,2,3};
	int[] expectedResult = {3,3,3};
	
	int[] actualResult = maxEnd3.makeArray(nums);
	Assert.assertArrayEquals(expectedResult,actualResult);
	}
	@Test
	public void first_number_biggest() {
	int[] nums = {3,2,1};
	int[] expectedResult = {3,3,3};
	
	int[] actualResult = maxEnd3.makeArray(nums);
	Assert.assertArrayEquals(expectedResult,actualResult);
	}
	@Test
	public void middle_number_biggest() {
	int[] nums = {2,3,1};
	int[] expectedResult = {2,2,2};
	
	int[] actualResult = maxEnd3.makeArray(nums);
	Assert.assertArrayEquals(expectedResult,actualResult);
	}
	@Test
	public void first_and_last_number_equal() {
	int[] nums = {2,3,2};
	int[] expectedResult = {2,2,2};
	
	int[] actualResult = maxEnd3.makeArray(nums);
	Assert.assertArrayEquals(expectedResult,actualResult);
	}
	
}
