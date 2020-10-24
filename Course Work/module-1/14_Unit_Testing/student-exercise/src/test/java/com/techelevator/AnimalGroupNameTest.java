package com.techelevator;


import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test;

public class AnimalGroupNameTest {

	private AnimalGroupName animalGroupName;
	
	@Before
	public void setup() {
		animalGroupName = new AnimalGroupName();
	}
	@Test
	public void rhino_returns_Crash() {
		String getHerd = animalGroupName.getHerd("rhino");
		Assert.assertEquals("Crash",getHerd);
	}
	@Test
	public void case_doesnt_matter() {
		String getHerd = animalGroupName.getHerd("Rhino");
		Assert.assertEquals("Crash",getHerd);
	}
	@Test
	public void return_unknown_if_not_listed() {
		String getHerd = animalGroupName.getHerd("cat");
		Assert.assertEquals("unknown",getHerd);
	}

}
