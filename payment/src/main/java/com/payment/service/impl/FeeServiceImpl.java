package com.payment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.entity.Fee;
import com.payment.repository.FeeRepository;
import com.payment.service.FeeService;

@Service
public class FeeServiceImpl implements FeeService{
	@Autowired
	private FeeRepository feeRepo;

	@Override
	public double calculateFee(long feeId, double amount) {
		// TODO Auto-generated method stub
		Fee fee = feeRepo.findById(feeId).get();
		List<Long> amountMax = fee.getAmountMax();
		List<Long> amountMin = fee.getAmountMin();
 		List<Long> feeAmount = fee.getFeeAmount();
		
		double finalFeeAmount = 0;
		for(int i=0;i<amountMax.size();i++) {
			if(amount>=amountMin.get(i) && amount<=amountMax.get(i)) {
				finalFeeAmount = feeAmount.get(i);
				break;
			}
		}
		if(finalFeeAmount == 0) {
			finalFeeAmount = feeAmount.get(feeAmount.size()-1);
		}
		
		finalFeeAmount+=amount;
		
		return finalFeeAmount;	
	}

	@Override
	public long addFee(Fee fee) {
		// TODO Auto-generated method stub
		feeRepo.save(fee);
		return fee.getFeeId();
	}

	@Override
	public long getRecentFeeId() {
		// TODO Auto-generated method stub
		return feeRepo.findMaxFeeId();
	}

	@Override
	public Fee findFeeById(long feeId) {
		// TODO Auto-generated method stub
		return feeRepo.findById(feeId).get();
	}

	@Override
	public Fee getRecentFee() {
		// TODO Auto-generated method stub
		return feeRepo.findById(feeRepo.findMaxFeeId()).get();
	}
}
