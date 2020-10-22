package com.techelevator.model;

public class Query {

	private String question;
	
	public Query() {}
	
	public Query(String question) {
		this.question= question;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
