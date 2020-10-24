package com.techelevator.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Balance;
import com.techelevator.model.BalanceDao;

@RestController
public class BalanceController {

	private BalanceDao balanceDao;
	
	public BalanceController(BalanceDao dao) {
		this.balanceDao = dao;
	}
	
	@RequestMapping(path="/users/{id}/balance", method=RequestMethod.GET)
	public Balance getBalance(@PathVariable(name="id") int userId) {
		return balanceDao.getBalance(userId);
		
	}
	
}
