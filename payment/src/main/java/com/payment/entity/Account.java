package com.payment.entity;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;
	private long accountNumber;
	private String accountName;
	@Transient
	private double accountBalanceNumber;
	private String accountBalance;
	private String accountStatus;
	private Date updatedDatetime;
	
	public Account() {
		super();
	}
	public Account(long accountNumber, String accountName, double accountBalanceNumber, String accountStatus) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountBalanceNumber = accountBalanceNumber;
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		accountBalance = formatter.format(accountBalanceNumber);
		this.accountStatus = accountStatus;
		LocalDate date = LocalDate.now();
		updatedDatetime = Date.valueOf(date);
	}
	public long getAccountId() {
		return accountId;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getAccountBalance() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		Number number = null;
		try {
			number = formatter.parse(accountBalance);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number.doubleValue();
	}
	
	public void setAccountBalance(double accountBalanceNumber) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		this.accountBalance = formatter.format(accountBalanceNumber);
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", accountName=" + accountName
				+ ", accountBalance=" + accountBalance + ", accountStatus=" + accountStatus + ", updatedDatetime="
				+ updatedDatetime + "]";
	}
}
