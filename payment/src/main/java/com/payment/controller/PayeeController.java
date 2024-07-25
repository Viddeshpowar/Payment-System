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

import com.payment.entity.Payee;
import com.payment.service.PayeeService;

@RestController
@RequestMapping("/api")
public class PayeeController {
	@Autowired
	private PayeeService payeeService;
	
	@PostMapping("/payee")
	public ResponseEntity<String> addPayee(@RequestBody Payee payee) {
		// Validate dueDate

		// Convert java.sql.Date to java.time.LocalDate
		java.sql.Date sqlDate = payee.getDueDate();
		LocalDate dueDate = sqlDate.toLocalDate();

		if (dueDate.isBefore(LocalDate.now())) {
			return new ResponseEntity<>("Due date must be a present or future date", HttpStatus.BAD_REQUEST);
		}

		long payeeId = payeeService.addPayee(payee);
		return new ResponseEntity<>("Payee details saved successfully. Payee ID is: " + payeeId, HttpStatus.CREATED);
	}
	
	@GetMapping("/{payee-id}/payee")
	public ResponseEntity<Payee> getPayee(@PathVariable("payee-id")long payeeId){
		Payee payee = payeeService.getPayeeById(payeeId);
		return new ResponseEntity<>(payee,HttpStatus.OK);
	}
}
