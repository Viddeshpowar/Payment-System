package com.payment.dto;

public class PaymentDetailsRequestDTO {
	private long fromAccountId;
	private long payeeId;
	private long toAccountId;
	public PaymentDetailsRequestDTO(long fromAccountId, long payeeId, long toAccountId) {
		super();
		this.fromAccountId = fromAccountId;
		this.payeeId = payeeId;
		this.toAccountId = toAccountId;
	}
	public long getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public long getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(long payeeId) {
		this.payeeId = payeeId;
	}
	public long getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(long toAccountId) {
		this.toAccountId = toAccountId;
	}
	@Override
	public String toString() {
		return "PaymentDetailsRequestDTO [fromAccountId=" + fromAccountId + ", payeeId=" + payeeId + ", toAccountId="
				+ toAccountId + "]";
	}
	
}
