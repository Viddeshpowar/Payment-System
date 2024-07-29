package com.payment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.dto.PaymentDetailsDTO;
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
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private PayeeService payeeService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private FeeService feeService;

	@Override
	public List<PaymentDetailsDTO> getAllPayments() {
		// TODO Auto-generated method stub
		List<Payment> plist = paymentRepo.findAll();
		List<PaymentDetailsDTO> paymentDetailsList = new ArrayList<PaymentDetailsDTO>();
		for(Payment payment : plist) {
			paymentDetailsList.add(getPaymentById(payment.getPaymentId()));
		}
		return paymentDetailsList;
	}

	@Override
	public PaymentDetailsDTO getPaymentById(long paymentId) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepo.findById(paymentId).get();
		Account fromAccount = accountService.getAccountById(payment.getAccountId());
		Payee payee = payeeService.getPayeeById(payment.getPayeeId());
		Account toAccount = accountService.getPayeeAccountDetailsByName(payee.getPayeeName());
		Fee fee = feeService.findFeeById(payment.getFeeId());
		PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO(paymentId, fromAccount, payee, toAccount, fee,payment.getUpdatedDatetime());
		return paymentDetailsDTO;
	}

	@Override
	public long addPayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.save(payment);
		return payment.getPaymentId();
	}

	@Override
	public long addPaymentDetails(Payment payment) {
		// TODO Auto-generated method stub
		//From Account Id
		long fromAccountId = payment.getAccountId();
		
		
		// Payee
		long payeeId = payment.getPayeeId();
		Payee payee = payeeService.getPayeeById(payeeId);
		double amountDue = payee.getAmountDue();
		
		//Payee Account (to-Account details)
		String payeeName = payee.getPayeeName();
		Account toAccount = accountService.getPayeeAccountDetailsByName(payeeName);
		
		// payment
		double toAccountBalance = toAccount.getAccountBalance();
		toAccountBalance += amountDue;
		toAccount.setAccountBalance(toAccountBalance);
		toAccount.setUpdatedDatetime();

		double finalDueAmount = feeService.calculateFee(payment.getFeeId(), amountDue);

		// fromAccountId
		Account fromAccount = accountService.getAccountById(fromAccountId);
		double fromAccountBalance = fromAccount.getAccountBalance();
		fromAccountBalance -= finalDueAmount;
		fromAccount.setAccountBalance(fromAccountBalance);
		fromAccount.setUpdatedDatetime();
		amountDue = 0;
		payee.setAmountDue(amountDue);

		// to get fee on amount
		long paymentId = addPayment(payment);
		return paymentId;
	}

	@Override
	public Payment updatePayment(long paymentId, Payment payment) {
		// TODO Auto-generated method stub
		Optional<Payment> existingPayment = paymentRepo.findById(paymentId);
		if (existingPayment.isPresent()) {
			return paymentRepo.save(payment);
		} else {
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
