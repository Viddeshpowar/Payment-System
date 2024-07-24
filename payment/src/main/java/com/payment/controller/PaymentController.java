package com.payment.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.dto.PaymentDetailsDTO;
import com.payment.dto.PaymentDetailsRequestDTO;
import com.payment.entity.Account;
import com.payment.entity.Fee;
import com.payment.entity.Payee;
import com.payment.entity.Payment;
import com.payment.service.AccountService;
import com.payment.service.FeeService;
import com.payment.service.PayeeService;
import com.payment.service.PaymentService;

@RestController
@RequestMapping("/api")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PayeeService payeeService;
	
	@Autowired
	private FeeService feeService;
	
	@GetMapping("/payment")
	public ResponseEntity<List<Payment>> getAllPayment(){
		List<Payment> paymentList = paymentService.getAllPayments();
		return new ResponseEntity<>(paymentList,HttpStatus.OK);
	}
	
	@GetMapping("/{payment-id}/payment")
	public ResponseEntity<PaymentDetailsDTO> getPaymentById(@PathVariable("payment-id") long paymentId){
		Payment payment = paymentService.getPaymentById(paymentId);
		Account toAccount = accountService.getAccountById(payment.getAccountId());
		Payee payee = payeeService.getPayeeById(payment.getPayeeId());
		Fee fee = feeService.findFeeById(payment.getFeeId());
		Date updatedDate = payment.getUpdatedDatetime();
		PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO(paymentId, toAccount, payee, fee, updatedDate);
		return new ResponseEntity<>(paymentDetailsDTO, HttpStatus.OK);
	}
	
	@PostMapping("/payment")
	public ResponseEntity<String> createPayment(@RequestBody PaymentDetailsRequestDTO paymentDetailRequest){
		paymentService.addPaymentDetails(paymentDetailRequest);
		return null;
	}
	
	@PutMapping("/{payment-id}/payment")
	public ResponseEntity<Payment> updatePayment(@PathVariable("payment-id") long paymentId,@RequestBody Payment payment){
		Payment updatedPayment = paymentService.updatePayment(paymentId, payment);
		return new ResponseEntity(updatedPayment, HttpStatus.OK);	
	}
	

}
