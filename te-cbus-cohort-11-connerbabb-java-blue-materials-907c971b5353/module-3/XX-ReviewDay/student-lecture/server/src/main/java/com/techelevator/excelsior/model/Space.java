package com.techelevator.excelsior.model;

import java.time.LocalDate;

public class Space {
	private int spaceId;
	private int venueId;
	private String name;
	private Boolean isAccessible;
	private int openFrom;
	private int openTo;
	private double dailyRate;
	private int maxOccupancy;
	public int getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}
	public int getVenueId() {
		return venueId;
	}
	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsAccessible() {
		return isAccessible;
	}
	public void setIsAccessible(Boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	public int getOpenFrom() {
		return openFrom;
	}
	public void setOpenFrom(int i) {
		this.openFrom = i;
	}
	public int getOpenTo() {
		return openTo;
	}
	public void setOpenTo(int openTo) {
		this.openTo = openTo;
	}
	public double getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dailyRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((isAccessible == null) ? 0 : isAccessible.hashCode());
		result = prime * result + maxOccupancy;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + openFrom;
		result = prime * result + openTo;
		result = prime * result + spaceId;
		result = prime * result + venueId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Space other = (Space) obj;
		if (Double.doubleToLongBits(dailyRate) != Double.doubleToLongBits(other.dailyRate))
			return false;
		if (isAccessible == null) {
			if (other.isAccessible != null)
				return false;
		} else if (!isAccessible.equals(other.isAccessible))
			return false;
		if (maxOccupancy != other.maxOccupancy)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (openFrom != other.openFrom)
			return false;
		if (openTo != other.openTo)
			return false;
		if (spaceId != other.spaceId)
			return false;
		if (venueId != other.venueId)
			return false;
		return true;
	}
	
	
	

	
	
	
	
	
}
