package com.techelevator.tenmo.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.account.model.dao.Account;
import com.techelevator.tenmo.account.model.dao.AccountDao;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

	private AccountDao accountDao;
	
	public AccountController(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	@RequestMapping(path="/accounts", method=RequestMethod.GET) 
	public Account get(Principal principal) {
		return accountDao.get(principal.getName());
	}
	
}
