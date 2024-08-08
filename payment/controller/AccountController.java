package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.entity.Account;
import com.payment.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {
	@Autowired
	private AccountService accService;
	
	@PostMapping("/account")
	public ResponseEntity<String> addAccount(@RequestBody Account account){
		long accountId = accService.addAccount(account);
		return new ResponseEntity<String>("Account saved Succssfully Please not AccountId : "+ accountId,HttpStatus.CREATED);
	}
}
