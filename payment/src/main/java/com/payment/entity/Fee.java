package com.payment.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "Fee")
public class Fee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long feeId;
	private List<Long> feeAmount;
	private List<Long> amountMin;
	private List<Long> amountMax;
	private Date updatedDatetime;
	
	
	public Fee() {
		super();
	}
	public Fee(List<Long> feeAmount, List<Long> amountMin, List<Long> amountMax) {
		super();
		this.feeAmount = feeAmount;
		this.amountMin = amountMin;
		this.amountMax = amountMax;
		LocalDate date = LocalDate.now();
		updatedDatetime = Date.valueOf(date);
	}
	@PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.updatedDatetime = Date.valueOf(LocalDate.now());
    }
	public long getFeeId() {
		return feeId;
	}
	public List<Long> getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(List<Long> feeAmount) {
		this.feeAmount = feeAmount;
	}
	public List<Long> getAmountMin() {
		return amountMin;
	}
	public void setAmountMin(List<Long> amountMin) {
		this.amountMin = amountMin;
	}
	public List<Long> getAmountMax() {
		return amountMax;
	}
	public void setAmountMax(List<Long> amountMax) {
		this.amountMax = amountMax;
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
		return "Fee [feeId=" + feeId + ", feeAmount=" + feeAmount + ", amountMin=" + amountMin + ", amountMax="
				+ amountMax + ", updatedDatetime=" + updatedDatetime + "]";
	}
}
