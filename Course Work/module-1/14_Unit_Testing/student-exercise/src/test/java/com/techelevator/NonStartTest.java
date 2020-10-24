package com.techelevator;


import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class NonStartTest {
	private NonStart nonStart;
	
	@Before
	public void setup() {
		nonStart = new NonStart();
	}
	@Test
	public void hello_and_hello_equals_elloello() {
		// Arrange
		// Act
		String getPartialString = nonStart.getPartialString("Hello","Hello");
		// Assert
		Assert.assertEquals("elloello", getPartialString);
	}
	@Test
	public void h_and_h_equals__() {
		// Arrange
		// Act
		String getPartialString = nonStart.getPartialString("H","H");
		// Assert
		Assert.assertEquals("", getPartialString);
	}
	@Test
	public void string_length_less_than_one() {
		// Arrange
		// Act
		String getPartialString = nonStart.getPartialString("","hello");
		// Assert
		Assert.assertEquals("ello", getPartialString);
	}
	@Test
	public void string_length_less_than_one_2() {
		// Arrange
		// Act
		String getPartialString = nonStart.getPartialString("hello","");
		// Assert
		Assert.assertEquals("ello", getPartialString);
	}
	
}
