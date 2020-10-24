package com.techelevator;

import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class StringBitsTest {
	private StringBits stringBits;
	
	@Before
	public void setup() {
		stringBits = new StringBits();
	}
	@Test
	public void two_letters() {
		String expectedResult = "H";
		String result = stringBits.getBits("Hi");
		Assert.assertEquals(expectedResult,result);
	}
	@Test
	public void five_letters() {
		String expectedResult = "Hlo";
		String result = stringBits.getBits("Hello");
		Assert.assertEquals(expectedResult,result);
	}
	@Test
	public void nine_letters() {
		String expectedResult = "Hello";
		String result = stringBits.getBits("Heeololeo");
		Assert.assertEquals(expectedResult,result);
	}
}
