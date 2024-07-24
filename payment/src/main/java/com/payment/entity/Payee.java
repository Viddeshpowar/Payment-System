package com.payment.entity;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Payee")
public class Payee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long payeeId;
	@Column(name = "payee_number")
	private long payeeNumber;
	@Column(name = "payee_name")
	private String payeeName;
	@Transient
	private double amountDueNumber;
	@Column(name = "amount_due")
	private String amountDue;
	@Column(name = "due_date")
	private Date dueDate;
	@Column(name = "updated_datetime")
	private Date updatedDatetime;
	
	
	public Payee() {
		super();
	}
	public Payee(long payeeNumber, String payeeName, double amountDueNumber, Date dueDate) {
		super();
		this.payeeNumber = payeeNumber;
		this.payeeName = payeeName;
		this.amountDueNumber = amountDueNumber;
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		amountDue = formatter.format(amountDueNumber);
		this.dueDate = dueDate;
		LocalDate date = LocalDate.now();
		updatedDatetime = Date.valueOf(date);
	}
	public long getPayeeId() {
		return payeeId;
	}
	public long getPayeeNumber() {
		return payeeNumber;
	}
	public void setPayeeNumber(long payeeNumber) {
		this.payeeNumber = payeeNumber;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public double getAmountDue() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en","IN"));
		Number number = null;
		try {
			number = formatter.parse(amountDue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		amountDueNumber = number.doubleValue();
		return amountDueNumber;
	}
	public void setAmountDue(double amountDueNumber) {
		this.amountDueNumber = amountDueNumber;
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en","IN"));
		amountDue = formatter.format(amountDueNumber);
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
		return "Payee [payeeId=" + payeeId + ", payeeNumber=" + payeeNumber + ", payeeName=" + payeeName
				+ ", amountDue=" + amountDue + ", dueDate=" + dueDate + ", updatedDatetime=" + updatedDatetime + "]";
	}

}
