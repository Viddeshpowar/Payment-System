package com.payment.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.entity.Account;
import com.payment.repository.AccountRepository;
import com.payment.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accRepo;

	@Override
	public Account getAccountById(long accountId) {
		// TODO Auto-generated method stub
		return accRepo.findById(accountId).get();
	}

	@Override
	public long addAccount(Account account) {
		// TODO Auto-generated method stub
		accRepo.save(account);
		return accRepo.findMaxAccountId();
	}

	@Override
	public Account getPayeeAccountDetailsByName(String payeeName) {
		// TODO Auto-generated method stub
		return accRepo.findByAccountName(payeeName).get(0);
	}

	@Override
	public long getMaxAccount() {
		// TODO Auto-generated method stub
		return accRepo.findMaxAccountId();
	}
}
