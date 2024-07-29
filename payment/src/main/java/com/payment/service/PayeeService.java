package com.payment.service;

import com.payment.dto.PayeeAccountDTO;
import com.payment.entity.Payee;

public interface PayeeService {
	public PayeeAccountDTO getPayeeDetailsById(long payeeId);
	public long addPayee(Payee payee);
	
	public Payee getPayeeById(long payeeId);
}
