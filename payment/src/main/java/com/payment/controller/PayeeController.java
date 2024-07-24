package com.payment.controller;

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
	public ResponseEntity<String> addPayee(@RequestBody Payee payee){
		long payeeId = payeeService.addPayee(payee);
		return new ResponseEntity<>("Payee Details saved Successfully Payee Id is : "+payeeId, HttpStatus.CREATED);
	}
	
	@GetMapping("/{payee-id}/payee")
	public ResponseEntity<Payee> getPayee(@PathVariable("payee-id")long payeeId){
		Payee payee = payeeService.getPayeeById(payeeId);
		return new ResponseEntity<>(payee,HttpStatus.OK);
	}
}
