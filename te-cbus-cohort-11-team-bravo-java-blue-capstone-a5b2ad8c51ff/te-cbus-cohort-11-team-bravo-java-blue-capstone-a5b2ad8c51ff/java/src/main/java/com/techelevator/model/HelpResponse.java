package com.techelevator.model;

public class HelpResponse {
	
	private int id;
	private String body;
	
	public HelpResponse() {};
	
	public HelpResponse(int id, String body) {
		this.id = id;
		this.body = body;

	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
