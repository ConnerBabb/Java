package com.techelevator.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Query;
import com.techelevator.model.QueryDTO;
import com.techelevator.model.QuestionResponse;

import NLP.Lemmatize;

import com.techelevator.model.HelpResponse;

@RestController
@CrossOrigin
public class QueryController {

	private QueryDTO queryDAO;

	public QueryController(QueryDTO queryDAO) {
		this.queryDAO = queryDAO;
	}

	@RequestMapping(value = "/chat", method = RequestMethod.POST)
	public List<HelpResponse> responses(@Valid @RequestBody Query query) {
		return queryDAO.getCategories(query);
	}

	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public List<QuestionResponse> questionResponses(@Valid @RequestBody Query query) {
		Lemmatize lemmatize = new Lemmatize();
		lemmatize.setUpLemmatizer();
		List<QuestionResponse> responses = new ArrayList<QuestionResponse>();
		List<String> splitQuestion = lemmatize.gramify(lemmatize.lemmatize(query.getQuestion()));
		List<Integer> idList = new ArrayList<Integer>();

			responses.addAll(queryDAO.getResponses(splitQuestion));

		ArrayList<QuestionResponse> responsesWithoutDuplicates = new ArrayList<QuestionResponse>();
		for (QuestionResponse response : responses) {
			if(!idList.contains(response.getResponse_id())) {
				idList.add(response.getResponse_id());
				responsesWithoutDuplicates.add(response);
			}
		}
		return responsesWithoutDuplicates;
	}
}
