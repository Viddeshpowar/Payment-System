package com.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.entity.Payee;
import com.payment.repository.PayeeRepository;
import com.payment.service.PayeeService;

@Service
public class PayeeServiceImpl implements PayeeService{
	@Autowired
	private PayeeRepository payeeRepo;

	@Override
	public Payee getPayeeById(long payeeId) {
		// TODO Auto-generated method stub
		return payeeRepo.findById(payeeId).orElse(null);
	}

	@Override
	public long addPayee(Payee payee) {
		// TODO Auto-generated method stub
		payeeRepo.save(payee);
		return payee.getPayeeId();
		
		
	}
	
}
