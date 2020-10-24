
package com.techelevator;

public class HomeworkAssignment {
	private int totalMarks;
	private int possibleMarks;
	private String submitterName;
	private String letterGrade;
	
	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getSubmitterName() {
		return submitterName;
	}

	public void setSubmitterName(String submitterName) {
		this.submitterName = submitterName;
	}

	public int getPossibleMarks() {
		return possibleMarks;
	}

	public String getLetterGrade() {
		double percentageGrade = 100*((double)totalMarks/possibleMarks);
		if (percentageGrade < 60.0) {
			letterGrade =  "F";
		} else if (percentageGrade < 70.0) {
			letterGrade = "D";
		} else if (percentageGrade < 80.0) {
			letterGrade = "C";
		} else if (percentageGrade < 90.0) {
			letterGrade = "B";
		} else if(percentageGrade< 100.0) {
			letterGrade = "A";
		}
		return letterGrade;
	}

	public HomeworkAssignment(int possibleMarks) {
		this.possibleMarks = possibleMarks;
	}

}
