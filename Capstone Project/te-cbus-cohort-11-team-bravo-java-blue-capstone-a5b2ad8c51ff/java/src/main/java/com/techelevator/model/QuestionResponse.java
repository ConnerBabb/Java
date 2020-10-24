package com.techelevator.model;

public class QuestionResponse {
	
	private int response_id;
	private int category_id;
	private String type;
	private String title;
	private String mainLink;
	private String transcriptLink;
	private String slidesLink;
	
	public QuestionResponse() {}
	
	public int getResponse_id() {
		return response_id;
	}
	public void setResponse_id(int response_id) {
		this.response_id = response_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMainLink() {
		return mainLink;
	}

	public void setMainLink(String mainLink) {
		this.mainLink = mainLink;
	}

	public String getTranscriptLink() {
		return transcriptLink;
	}

	public void setTranscriptLink(String transcriptLink) {
		this.transcriptLink = transcriptLink;
	}

	public String getSlidesLink() {
		return slidesLink;
	}

	public void setSlidesLink(String slidesLink) {
		this.slidesLink = slidesLink;
	}

	


}
