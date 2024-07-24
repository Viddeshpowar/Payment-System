package com.payment.service;

import com.payment.entity.Payee;

public interface PayeeService {
	public Payee getPayeeById(long payeeId);
	public long addPayee(Payee payee);
}
