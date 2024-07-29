package com.payment.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "Payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentId;
	
	private long accountId;
	
	private long payeeId;
	
	private long feeId;
	
	private Date updatedDatetime;
	
	
	public Payment() {
		super();
	}



	public Payment(long accountId, long payeeId, long feeId) {
		super();
		this.accountId = accountId;
		this.payeeId = payeeId;
		this.feeId = feeId;
		LocalDate date = LocalDate.now();
		updatedDatetime = Date.valueOf(date);
	}
	@PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.updatedDatetime = Date.valueOf(LocalDate.now());
    }
	

	public long getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}



	public long getAccountId() {
		return accountId;
	}



	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}



	public long getPayeeId() {
		return payeeId;
	}



	public void setPayeeId(long payeeId) {
		this.payeeId = payeeId;
	}



	public long getFeeId() {
		return feeId;
	}



	public void setFeeId(long feeId) {
		this.feeId = feeId;
	}



	public Date getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime() {
		LocalDate date = LocalDate.now();
		updatedDatetime = Date.valueOf(date);
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", accountId=" + accountId + ", payeeId=" + payeeId + ", feeId="
				+ feeId + ", updatedDatetime=" + updatedDatetime + "]";
	}
}
