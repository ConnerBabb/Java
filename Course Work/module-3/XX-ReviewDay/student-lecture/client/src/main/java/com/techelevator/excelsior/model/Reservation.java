package com.techelevator.excelsior.model;

import java.time.LocalDate;

public class Reservation {
	
		private int resId;
		private int spaceId;
		private int numberOfAttendees;
		private LocalDate startDate;
		private int numberOfDays;
		private String reservedFor;
		private LocalDate endDate;
		
		
		public int getResId() {
			return resId;
		}
		public void setResId(int resId) {
			this.resId = resId;
		}
		public int getSpaceId() {
			return spaceId;
		}
		public void setSpaceId(int spaceId) {
			this.spaceId = spaceId;
		}
		public int getNumberOfAttendees() {
			return numberOfAttendees;
		}
		public void setNumberOfAttendees(int numberOfAttendees) {
			this.numberOfAttendees = numberOfAttendees;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public int getNumberOfDays() {
			return numberOfDays;
		}
		public void setNumberOfDays(int numberOfDays) {
			this.numberOfDays = numberOfDays;
		}
		public String getReservedFor() {
			return reservedFor;
		}
		public void setReservedFor(String reservedFor) {
			this.reservedFor = reservedFor;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
			result = prime * result + numberOfAttendees;
			result = prime * result + resId;
			result = prime * result + ((reservedFor == null) ? 0 : reservedFor.hashCode());
			result = prime * result + spaceId;
			result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
			Reservation other = (Reservation) obj;
			if (endDate == null) {
				if (other.endDate != null)
					return false;
			} else if (!endDate.equals(other.endDate))
				return false;
			if (numberOfAttendees != other.numberOfAttendees)
				return false;
			if (resId != other.resId)
				return false;
			if (reservedFor == null) {
				if (other.reservedFor != null)
					return false;
			} else if (!reservedFor.equals(other.reservedFor))
				return false;
			if (spaceId != other.spaceId)
				return false;
			if (startDate == null) {
				if (other.startDate != null)
					return false;
			} else if (!startDate.equals(other.startDate))
				return false;
			return true;
		}
	
		
}
		
