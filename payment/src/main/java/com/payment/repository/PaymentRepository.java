package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT COALESCE(MAX(p.paymentId), 0) FROM Payment p")
    public long findMaxPaymentId();
}
