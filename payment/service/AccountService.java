package com.payment.service;

import com.payment.entity.Account;

public interface AccountService {
	public Account getAccountById(long accountId);
	
	public long addAccount(Account account);
	
	public Account getPayeeAccountDetailsByName(String payeeName);
	
	public long getMaxAccount();
}
