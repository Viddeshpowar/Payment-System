package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.entity.Fee;
import com.payment.service.FeeService;

@RestController
@RequestMapping("/api")
public class FeeController {
	@Autowired
	private FeeService feeService;
	
	@PostMapping("/fee")
	public ResponseEntity<String> addFee(@RequestBody Fee fee){
		long feeId = feeService.addFee(fee);
		return new ResponseEntity<>("Fee is saved Successfully & feeId is : "+feeId,HttpStatus.OK);
	}
	
	@GetMapping("/{fee-id}/fee")
	public ResponseEntity<Fee> getFeeById(@PathVariable("fee-id")long feeId){
		Fee fee = feeService.findFeeById(feeId);
		return new ResponseEntity<>(fee, HttpStatus.OK);
	}
	
	@GetMapping("/fee/{fee-id}/{amount}/calculate")
	public ResponseEntity<Double> calculateFee(@PathVariable("fee-id") long feeId,@PathVariable double amount){
		Double fee = feeService.calculateFee(feeId, amount);
		return new ResponseEntity<Double>(fee,HttpStatus.OK);
	}
}
