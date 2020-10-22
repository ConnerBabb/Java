package com.techelevator;


import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class CigarPartyTest {
	private CigarParty cigarParty;
	
	@Before
	public void setup() {
		cigarParty = new CigarParty();
	}
	@Test
	public void successful_fail_below_40() {
		boolean result = cigarParty.haveParty(39,false);
		Assert.assertFalse(result);
	}
	@Test
	public void successful_true_if_above_40_and_weekend() {
		boolean result = cigarParty.haveParty(80,true);
		Assert.assertTrue(result);
	}
	@Test
	public void successful_false_if_above_60_and_weekday() {
		boolean result = cigarParty.haveParty(80,false);
		Assert.assertFalse(result);
	}
	@Test
	public void successful_true_if_above_40_and_below_60_and_weekday() {
		boolean result = cigarParty.haveParty(50,false);
		Assert.assertTrue(result);
	}
}
