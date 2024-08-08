package com.payment.dto;

import java.sql.Date;

import com.payment.entity.Account;
import com.payment.entity.Fee;
import com.payment.entity.Payee;

public class PaymentDetailsDTO {
	private long paymentId;
	private Account fromAccount;
	private Payee payee;
	private Account toAccount;
	private Fee fee;
	private Date updatedDate;
	
	public PaymentDetailsDTO() {
		super();
	}

	public PaymentDetailsDTO(long paymentId, Account fromAccount, Payee payee, Account toAccount, Fee fee,
			Date updatedDate) {
		super();
		this.paymentId = paymentId;
		this.fromAccount = fromAccount;
		this.payee = payee;
		this.toAccount = toAccount;
		this.fee = fee;
		this.updatedDate = updatedDate;
	}

	public long getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}


	public Account getFromAccount() {
		return fromAccount;
	}


	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}


	public Account getToAccount() {
		return toAccount;
	}


	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}


	public Payee getPayee() {
		return payee;
	}


	public void setPayee(Payee payee) {
		this.payee = payee;
	}


	public Fee getFee() {
		return fee;
	}


	public void setFee(Fee fee) {
		this.fee = fee;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	@Override
	public String toString() {
		return "PaymentDetailsDTO [paymentId=" + paymentId + ", fromAccount=" + fromAccount + ", toAccount=" + toAccount
				+ ", payee=" + payee + ", fee=" + fee + ", updatedDate=" + updatedDate + "]";
	}

}
