package com.techelevator.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Query;
import com.techelevator.model.QueryDTO;
import com.techelevator.model.QuestionResponse;
import com.techelevator.model.HelpResponse;

@Service
public class QuerySqlDAO implements QueryDTO {

	private JdbcTemplate jdbcTemplate;

	public QuerySqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<HelpResponse> getCategories(Query query) {

		List<HelpResponse> responses = new ArrayList<HelpResponse>();

		String sql = "SELECT category_id, category FROM category";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

		while (rowSet.next()) {
			HelpResponse response = new HelpResponse();
			response = mapRowToResponse(rowSet);
			responses.add(response);
		}
		return responses;
	}

	@Override
	public List<QuestionResponse> getResponses(List<String> words) {
		List<QuestionResponse> responses = new LinkedList<QuestionResponse>();
		String sql = "SELECT term , name , t.response_id , category_id, (tfidf / 1000) as tfidf, type,main_link,transcript_link,slides_link\n" + 
				"FROM term t\n" + 
				"JOIN response r ON t.response_id = r.response_id\n" + 
				"WHERE term IN ("+makeQuestionMarks(words)+") and type = 'Pathway'\n" + 
				"UNION\n" + 
				"SELECT term , name , t.response_id , category_id, (tfidf) as tfidf, type,main_link,transcript_link,slides_link\n" + 
				"FROM term t\n" + 
				"JOIN response r ON t.response_id = r.response_id\n" + 
				"WHERE term IN ("+makeQuestionMarks(words)+") and type = 'Zoom'\n" + 
				"UNION\n" + 
				"SELECT term , name , t.response_id , category_id, (tfidf / 50) as tfidf, type,main_link,transcript_link,slides_link\n" + 
				"FROM term t\n" + 
				"JOIN response r ON t.response_id = r.response_id\n" + 
				"WHERE term IN ("+makeQuestionMarks(words)+") and type = 'Book'\n" + 
				"ORDER BY tfidf DESC\n" + 
				"LIMIT 5";
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,formatWords(words));

		while (rowSet.next()) {
			QuestionResponse response = new QuestionResponse();
			response = mapRowToQuestionResponse(rowSet);
			responses.add(response);
		}
		return responses;
	}

	private HelpResponse mapRowToResponse(SqlRowSet row) {
		HelpResponse response = new HelpResponse();

		response.setId(row.getInt("category_id"));
		response.setBody(row.getString("category"));

		return response;
	}
	
	private QuestionResponse mapRowToQuestionResponse(SqlRowSet row) {
		
		QuestionResponse response = new QuestionResponse();

		response.setCategory_id(row.getInt("category_id"));
		response.setResponse_id(row.getInt("response_id"));
		response.setType(row.getString("type"));
		response.setTitle(row.getString("name"));
		response.setMainLink(row.getString("main_link"));
		response.setTranscriptLink(row.getString("transcript_link"));
		response.setSlidesLink(row.getString("slides_link"));
		
		return response;
	}
	private Object[] formatWords(List<String> words) {
		List<String> wordsList = new ArrayList<String>();
		for(String word : words) {
			if(!word.equals("")&&!word.equals("how")&&!word.equals("to")
					&&!word.equals("what")&&!word.equals("is")&&!word.equals("do")
					&&!word.equals("i")&&!word.equals("are")&&!word.equals("find")
					&&!word.equals("where")&&!word.equals("a")&&!word.equals("when")
					&&!word.equals("for")) {
				wordsList.add(word);
			}
		}
		List<String> wordsListAll = new ArrayList<String>();
		wordsListAll.addAll(wordsList);
		wordsListAll.addAll(wordsList);
		wordsListAll.addAll(wordsList);
		String[] wordsArr = new String[wordsListAll.size()];
		wordsListAll.toArray(wordsArr);
		return (Object[]) wordsArr;
	}
	private String makeQuestionMarks(List<String> words) {
		List<String> wordsList = new ArrayList<String>();
		for(String word : words) {
			if(!word.equals("")&&!word.equals("how")&&!word.equals("to")
					&&!word.equals("what")&&!word.equals("is")&&!word.equals("do")
					&&!word.equals("i")&&!word.equals("are")&&!word.equals("find")
					&&!word.equals("where")&&!word.equals("a")&&!word.equals("when")
					&&!word.equals("for")) {
				wordsList.add(word);
			}
		}
		int num = wordsList.size();
		String questionMarks = "";
		for (int i = 0 ; i < num; i++) {
			if(!(i +1 == num)) {
				questionMarks = questionMarks + "?,";
			} else {
			questionMarks = questionMarks + "?";
			}
		}
		return questionMarks;
		
	}
}
