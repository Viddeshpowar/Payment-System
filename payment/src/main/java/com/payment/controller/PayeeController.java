package com.payment.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.dto.PayeeAccountDTO;
import com.payment.entity.Account;
import com.payment.entity.Payee;
import com.payment.service.AccountService;
import com.payment.service.PayeeService;

@RestController
@RequestMapping("/api")
public class PayeeController {
	@Autowired
	private PayeeService payeeService;
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/payee")
	public ResponseEntity<String> addPayee(@RequestBody PayeeAccountDTO PayeeAccountDTO) {
		// Validate dueDate
		Account toAccount = PayeeAccountDTO.getToAccount();
		// Convert java.sql.Date to java.time.LocalDate
		Payee payee = PayeeAccountDTO.getPayee();
		java.sql.Date sqlDate = payee.getDueDate();
		LocalDate dueDate = sqlDate.toLocalDate();

		if (dueDate.isBefore(LocalDate.now())) {
			return new ResponseEntity<>("Due date must be a present or future date", HttpStatus.BAD_REQUEST);
		}
		long payeeId = payeeService.addPayee(payee);
		long accountId = accountService.addAccount(toAccount);
		return new ResponseEntity<>("Payee details saved successfully. Payee ID is: " + payeeId+" Payee's Account Id is :"+accountId, HttpStatus.CREATED);
	}
	
	@GetMapping("/{payee-id}/payee")
	public ResponseEntity<PayeeAccountDTO> getPayee(@PathVariable("payee-id")long payeeId){
		PayeeAccountDTO payeeAccountDTO = payeeService.getPayeeDetailsById(payeeId);
		return new ResponseEntity<>(payeeAccountDTO,HttpStatus.OK);
	}
}
