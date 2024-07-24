package com.payment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.dto.PaymentDetailsRequestDTO;
import com.payment.entity.Account;
import com.payment.entity.Fee;
import com.payment.entity.Payee;
import com.payment.entity.Payment;
import com.payment.repository.PaymentRepository;
import com.payment.service.AccountService;
import com.payment.service.FeeService;
import com.payment.service.PayeeService;
import com.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private PayeeService payeeService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private FeeService feeService;

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepo.findAll();
	}

	@Override
	public Payment getPaymentById(long paymentId) {
		// TODO Auto-generated method stub
		return paymentRepo.findById(paymentId).orElse(null);
	}

	@Override
	public long addPayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.save(payment);
		return payment.getPaymentId();
	}

	@Override
	public long addPaymentDetails(PaymentDetailsRequestDTO paymentDetailRequest) {
		// TODO Auto-generated method stub
		
		//Payee
		long payeeId = paymentDetailRequest.getPayeeId();
		Payee payee = payeeService.getPayeeById(payeeId);
		double amountDue = payee.getAmountDue();
		
		
		// payment
		long toAccountId = paymentDetailRequest.getToAccountId();
		Account toAccount = accountService.getAccountById(toAccountId);
		double toAccountBalance = toAccount.getAccountBalance();
		toAccountBalance += amountDue;
		toAccount.setAccountBalance(toAccountBalance);
		toAccount.setUpdatedDatetime();
		
		
		double finalDueAmount = feeService.calculateFee(amountDue);
		
		//fromAccountId
		long fromAccountId = paymentDetailRequest.getFromAccountId();
		Account fromAccount = accountService.getAccountById(fromAccountId);
		double fromAccountBalance = fromAccount.getAccountBalance();
		fromAccountBalance -= finalDueAmount;
		fromAccount.setAccountBalance(fromAccountBalance);
		fromAccount.setUpdatedDatetime();
		amountDue = 0;
		payee.setAmountDue(amountDue);
		
		//to get fee on amount
		Fee fee = feeService.getRecentFee();
		
		Payment payment = new Payment(toAccountId, payeeId, fee.getFeeId());
		long paymentId = addPayment(payment);
		return paymentId;
	}

	@Override
	public Payment updatePayment(long paymentId, Payment payment) {
		// TODO Auto-generated method stub
		Optional<Payment> existingPayment = paymentRepo.findById(paymentId);
		if(existingPayment.isPresent()) {
			return paymentRepo.save(payment);
		}else {
			return null;
		}
		
	}

	@Override
	public boolean deletePayment(long paymentId) {
		// TODO Auto-generated method stub
		paymentRepo.deleteById(paymentId);
		return true;
	}
	
}
