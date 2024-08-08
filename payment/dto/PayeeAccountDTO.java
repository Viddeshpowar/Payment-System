package com.payment.dto;

import com.payment.entity.Account;
import com.payment.entity.Payee;

public class PayeeAccountDTO {
	private Payee payee;
	private Account toAccount;
	public PayeeAccountDTO(Payee payee, Account toAccount) {
		super();
		this.payee = payee;
		this.toAccount = toAccount;
	}
	public Payee getPayee() {
		return payee;
	}
	public void setPayee(Payee payee) {
		this.payee = payee;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	@Override
	public String toString() {
		return "PayeeAccountDTO [payee=" + payee + ", toAccount=" + toAccount + "]";
	}
	
	
}
