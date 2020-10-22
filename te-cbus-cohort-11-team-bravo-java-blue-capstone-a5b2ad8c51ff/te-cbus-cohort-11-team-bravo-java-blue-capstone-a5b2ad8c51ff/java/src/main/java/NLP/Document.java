package NLP;

import java.util.List;

public class Document {

	private String name;
	private String type;
	private List<String> grams;
	private String mainLink;
	private String transcipt;
	private String slides;
	private int category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGrams() {
		return grams;
	}

	public void setGrams(List<String> grams) {
		this.grams = grams;
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

	public String getTranscipt() {
		return transcipt;
	}

	public void setTranscipt(String transcipt) {
		this.transcipt = transcipt;
	}

	public String getSlides() {
		return slides;
	}

	public void setSlides(String slides) {
		this.slides = slides;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}


}
