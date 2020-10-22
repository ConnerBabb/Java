package com.techelevator;

public class Airplane {
 public String planeNumber;
 public int bookedFirstClassSeats;
 public int totalFirstClassSeats;
 public int bookedCoachSeats;
 public int totalCoachSeats;
public String getPlaneNumber() {
	return planeNumber;
}
public int getBookedFirstClassSeats() {
	return bookedFirstClassSeats;
}
public int getAvailableFirstClassSeats() {
	return totalFirstClassSeats - bookedFirstClassSeats;
}
public int getTotalFirstClassSeats() {
	return totalFirstClassSeats;
}
public int getBookedCoachSeats() {
	return bookedCoachSeats;
}
public int getAvailableCoachSeats() {
	return totalCoachSeats - bookedCoachSeats;
}
public int getTotalCoachSeats() {
	return totalCoachSeats;
}
public Airplane(String planeNumber, int totalFirstClassSeats, int totalCoachSeats){
	this.totalFirstClassSeats = totalFirstClassSeats;
	this.totalCoachSeats = totalCoachSeats;
	this.planeNumber = planeNumber;
}

public boolean reserveSeats(boolean forFirstClass, int totalNumberOfSeats) {
	if (forFirstClass) {
		if (getAvailableFirstClassSeats() >= totalNumberOfSeats) {
		bookedFirstClassSeats += totalNumberOfSeats;
			return true;
		}
		} if(!forFirstClass) {
		if (getAvailableCoachSeats() >= totalNumberOfSeats) {
		bookedCoachSeats += totalNumberOfSeats;
		return true;
		} 
}	return false;	
 
}
}