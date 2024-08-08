package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payment.entity.Payee;


@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long>{
	@Query("SELECT MAX(p.payeeId) FROM Payee p")
    public long findMaxPayeeId();
}
