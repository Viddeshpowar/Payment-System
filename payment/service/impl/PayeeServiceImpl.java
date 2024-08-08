package com.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.dto.PayeeAccountDTO;
import com.payment.entity.Account;
import com.payment.entity.Payee;
import com.payment.repository.PayeeRepository;
import com.payment.service.AccountService;
import com.payment.service.PayeeService;

@Service
public class PayeeServiceImpl implements PayeeService{
	@Autowired
	private PayeeRepository payeeRepo;
	
	@Autowired 
	private AccountService accountService;

	@Override
	public PayeeAccountDTO getPayeeDetailsById(long payeeId) {
		// TODO Auto-generated method stub
		Payee payee = payeeRepo.findById(payeeId).get();
		String payeeName = payee.getPayeeName();
		Account payeeAccount = accountService.getPayeeAccountDetailsByName(payeeName);
		return new PayeeAccountDTO(payee, payeeAccount);
	}
	

	@Override
	public long addPayee(Payee payee) {
		// TODO Auto-generated method stub
		payeeRepo.save(payee);
		return payee.getPayeeId();
	}


	@Override
	public Payee getPayeeById(long payeeId) {
		// TODO Auto-generated method stub
		return payeeRepo.findById(payeeId).get();
	}	
}
