package com.payment.service;

import java.util.List;

import com.payment.dto.PaymentDetailsRequestDTO;
import com.payment.entity.Payment;

public interface PaymentService {
	public List<Payment> getAllPayments();
	
	public Payment getPaymentById(long paymentId);
	
	public long addPaymentDetails(long fromAccountId, Payment payment);
	
	public long addPayment(Payment payment);
	
	public Payment updatePayment(long paymentId, Payment payment);
	
	public boolean deletePayment(long paymentId);
}
