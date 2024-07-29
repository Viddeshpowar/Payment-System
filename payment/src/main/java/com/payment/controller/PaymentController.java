package com.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.dto.PaymentDetailsDTO;
import com.payment.entity.Payment;
import com.payment.service.PaymentService;

@RestController
@RequestMapping("/api")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/payment")
	public ResponseEntity<List<PaymentDetailsDTO>> getAllPayment(){
		List<PaymentDetailsDTO> paymentDetails = paymentService.getAllPayments();
		return new ResponseEntity<>(paymentDetails,HttpStatus.OK);
	}
	
	@GetMapping("/{payment-id}/payment")
	public ResponseEntity<PaymentDetailsDTO> getPaymentById(@PathVariable("payment-id") long paymentId){
		PaymentDetailsDTO paymentDetailsDTO = paymentService.getPaymentById(paymentId);
		return new ResponseEntity<PaymentDetailsDTO>(paymentDetailsDTO,HttpStatus.FOUND);
	}
	
	@PostMapping("/payment")
	public ResponseEntity<String> createPayment(@RequestBody Payment payment){
		paymentService.addPaymentDetails(payment);
		return new ResponseEntity<>("Payment Details Saved Successfully",HttpStatus.CREATED);
	}
	
	@PutMapping("/{payment-id}/payment")
	public ResponseEntity<Payment> updatePayment(@PathVariable("payment-id") long paymentId,@RequestBody Payment payment){
		Payment updatedPayment = paymentService.updatePayment(paymentId, payment);
		return new ResponseEntity<>(updatedPayment, HttpStatus.OK);	
	}
	
	@DeleteMapping("{payment-id}/payment")
	public ResponseEntity<String> deletePayment(@PathVariable("payment-id")long paymentId){
		try {
			paymentService.deletePayment(paymentId);
			PaymentDetailsDTO paymentDetailsDTO = paymentService.getPaymentById(paymentId);
			if(paymentDetailsDTO.equals(null)) {
				return new ResponseEntity<>("payment is deleted", HttpStatus.OK);
			}
			return new ResponseEntity<>("payment is not delted", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
	        return new ResponseEntity<>("An error occurred while deleting the payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
