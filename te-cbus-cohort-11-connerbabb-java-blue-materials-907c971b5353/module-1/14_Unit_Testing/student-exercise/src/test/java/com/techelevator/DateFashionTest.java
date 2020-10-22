package com.techelevator;

import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class DateFashionTest {
	private DateFashion dateFashion;
	
	@Before
	public void setup() {
		dateFashion = new DateFashion();
	}
	@Test
	public void is_very_stylish() {
		int expectedResult = 2;
		int result = dateFashion.getATable(5,8);
		Assert.assertEquals(expectedResult, result);
	}
	@Test
	public void is_very_stylish_2() {
		int expectedResult = 2;
		int result = dateFashion.getATable(8,5);
		Assert.assertEquals(expectedResult, result);
	}
	@Test
	public void is_maybe() {
		int expectedResult = 1;
		int result = dateFashion.getATable(5,5);
		Assert.assertEquals(expectedResult, result);
	}
	@Test
	public void dont_get_table() {
		int expectedResult = 0;
		int result = dateFashion.getATable(2,5);
		Assert.assertEquals(expectedResult, result);
	}
	@Test
	public void dont_get_table_2() {
		int expectedResult = 0;
		int result = dateFashion.getATable(5,2);
		Assert.assertEquals(expectedResult, result);
	}
}
