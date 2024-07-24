package com.payment.dto;

import java.sql.Date;

import com.payment.entity.Account;
import com.payment.entity.Fee;
import com.payment.entity.Payee;
import com.payment.entity.Payment;

public class PaymentDetailsDTO {
	private long paymentId;
	private Account toAccount;
	private Payee payee;
	private Fee fee;
	private Date updatedDate;
	
	public PaymentDetailsDTO(long paymentId, Account toAccount, Payee payee, Fee fee, Date updatedDate) {
		super();
		this.paymentId = paymentId;
		this.toAccount = toAccount;
		this.payee = payee;
		this.fee = fee;
		this.updatedDate = updatedDate;
	}

	public long getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
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
		return "PaymentDetailsDTO [paymentId=" + paymentId + ", toAccount=" + toAccount + ", payee=" + payee + ", fee="
				+ fee + ", updatedDate=" + updatedDate + "]";
	}
}
