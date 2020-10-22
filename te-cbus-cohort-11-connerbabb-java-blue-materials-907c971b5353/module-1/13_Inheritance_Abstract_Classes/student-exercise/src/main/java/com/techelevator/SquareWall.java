package com.techelevator;

public class SquareWall extends RectangleWall{
	public SquareWall(String name, String color, int sideLength) {
		super(name, color, sideLength, sideLength);
		this.sideLength = sideLength;
	}
	private int sideLength;
	


	@Override
	public int getArea() {
		// TODO Auto-generated method stub
		return sideLength*sideLength;
	}
	@Override
	public String toString() {
		return getName() + " ("+sideLength+"x"+sideLength+") " + "square";
	}

}
