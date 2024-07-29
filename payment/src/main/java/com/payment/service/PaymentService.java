package com.payment.service;

import java.util.List;

import com.payment.dto.PaymentDetailsDTO;
import com.payment.entity.Payment;

public interface PaymentService {
	public List<PaymentDetailsDTO> getAllPayments();
	
	public PaymentDetailsDTO getPaymentById(long paymentId);
	
	public long addPaymentDetails(Payment payment);
	
	public long addPayment(Payment payment);
	
	public Payment updatePayment(long paymentId, Payment payment);
	
	public boolean deletePayment(long paymentId);
}
