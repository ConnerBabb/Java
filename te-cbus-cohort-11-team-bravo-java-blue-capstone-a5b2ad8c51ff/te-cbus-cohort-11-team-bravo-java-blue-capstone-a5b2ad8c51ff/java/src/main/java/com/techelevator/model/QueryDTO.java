package com.techelevator.model;

import java.util.List;

public interface QueryDTO {
	
	List<HelpResponse> getCategories(Query query);
	List<QuestionResponse> getResponses(List<String> word);   

}
