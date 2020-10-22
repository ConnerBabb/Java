package com.techelevator;

public class RectangleWall extends Wall {

	private int height;
	private int length;



	public int getHeight() {
		return height;
	}


	public int getLength() {
		return length;
	}
	
	public RectangleWall(String name,String color,int length,int height) {
		super(name, color);
		this.height = height;
		this.length = length;
	}

	@Override
	public int getArea() {
		// TODO Auto-generated method stub
		return length * height;
	}

	@Override
	public String toString() {
		return getName() + " ("+getLength()+"x"+getHeight()+") " + "rectangle";
	}

}
