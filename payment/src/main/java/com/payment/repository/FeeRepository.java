package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payment.entity.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long>{
	@Query("SELECT MAX(f.feeId) FROM Fee f")
    public long findMaxFeeId();
	
}
