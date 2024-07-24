package com.payment.service;

import com.payment.entity.Fee;

public interface FeeService {
	public double calculateFee(double amountDue);
	
	public long addFee(Fee fee);
	
	public long getRecentFeeId();
	
	public Fee findFeeById(long feeId);
	
	public Fee getRecentFee();
	
	
}
