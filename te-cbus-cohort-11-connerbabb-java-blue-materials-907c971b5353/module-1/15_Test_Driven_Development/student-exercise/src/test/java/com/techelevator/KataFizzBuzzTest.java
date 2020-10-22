package com.techelevator;

import org.junit.*;
import static org.junit.Assert.*;

public class KataFizzBuzzTest {
	private KataFizzBuzz kataFizzBuzz;
	
	@Before
	public void setup() {
		kataFizzBuzz = new KataFizzBuzz();
	}
	@Test
	public void one_returns_one() {
		Assert.assertEquals("1" , kataFizzBuzz.fizzBuzz(1));
	}
	@Test
	public void divisible_by_3_equals_Fizz() {
		Assert.assertEquals("Fizz",kataFizzBuzz.fizzBuzz(3));
	}
	@Test
	public void divisible_by_five_equals_Buzz() {
		Assert.assertEquals("Buzz",kataFizzBuzz.fizzBuzz(5));
	}
	@Test
	public void divisible_by_five_and_three_equals_fizzBuzz() {
		Assert.assertEquals("FizzBuzz", kataFizzBuzz.fizzBuzz(15));
	}
	@Test
	public void greater_than_100_or_less_than_1_empty() {
		Assert.assertEquals("", kataFizzBuzz.fizzBuzz(0));
	}
	@Test
	public void contains_three() {
		Assert.assertEquals("Fizz", kataFizzBuzz.fizzBuzz(13));
	}
	@Test
	public void contains_five() {
		Assert.assertEquals("Buzz", kataFizzBuzz.fizzBuzz(51));
	}
	@Test
	public void contains_five_and_three() {
		Assert.assertEquals("FizzBuzz", kataFizzBuzz.fizzBuzz(53));
	}
}
