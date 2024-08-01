package com.payment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payment.entity.Account;
import com.payment.entity.Fee;
import com.payment.entity.Payee;
import com.payment.entity.Payment;
import com.payment.repository.PaymentRepository;
import com.payment.service.AccountService;
import com.payment.service.FeeService;
import com.payment.service.PayeeService;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepo;

    @Mock
    private PayeeService payeeService;

    @Mock
    private AccountService accountService;

    @Mock
    private FeeService feeService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment;
    private Payee payee;
    private Account fromAccount;
    private Account toAccount;
    private Fee fee;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setPaymentId(1L);
        payment.setAccountId(1L);
        payment.setPayeeId(1L);
        payment.setFeeId(1L);

        payee = new Payee();
        payee.setPayeeId(1L);
        payee.setPayeeName("John Doe");
        payee.setAmountDue(100.0);

        fromAccount = new Account();
        fromAccount.setAccountId(1L);
        fromAccount.setAccountBalance(1000.0);

        toAccount = new Account();
        toAccount.setAccountId(2L);
        toAccount.setAccountBalance(500.0);

        fee = new Fee();
        fee.setFeeId(1L);
        fee.setAmountMax(Arrays.asList(100L, 500L, 1000L));
        fee.setAmountMin(Arrays.asList(0L, 100L, 500L));
        fee.setFeeAmount(Arrays.asList(10L, 20L, 30L));
    }

    @Test
    void testGetAllPayments() {
        when(paymentRepo.findAll()).thenReturn(Arrays.asList(payment));
        when(paymentRepo.findById(1L)).thenReturn(Optional.of(payment));
        when(accountService.getAccountById(1L)).thenReturn(fromAccount);
        when(payeeService.getPayeeById(1L)).thenReturn(payee);
        when(accountService.getPayeeAccountDetailsByName("John Doe")).thenReturn(toAccount);
        when(feeService.findFeeById(1L)).thenReturn(fee);

        var result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(paymentRepo).findAll();
    }

    @Test
    void testGetPaymentById() {
        when(paymentRepo.findById(1L)).thenReturn(Optional.of(payment));
        when(accountService.getAccountById(1L)).thenReturn(fromAccount);
        when(payeeService.getPayeeById(1L)).thenReturn(payee);
        when(accountService.getPayeeAccountDetailsByName("John Doe")).thenReturn(toAccount);
        when(feeService.findFeeById(1L)).thenReturn(fee);

        var result = paymentService.getPaymentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getPaymentId());
        verify(paymentRepo).findById(1L);
    }

    @Test
    void testAddPayment() {
        when(paymentRepo.save(any(Payment.class))).thenReturn(payment);

        var result = paymentService.addPayment(payment);

        assertEquals(1L, result);
        verify(paymentRepo).save(payment);
    }

    @Test
    void testAddPaymentDetails() {
        when(paymentRepo.save(any(Payment.class))).thenReturn(payment);
        when(payeeService.getPayeeById(1L)).thenReturn(payee);
        when(accountService.getPayeeAccountDetailsByName("John Doe")).thenReturn(toAccount);
        when(accountService.getAccountById(1L)).thenReturn(fromAccount);
        when(feeService.calculateFee(1L, 100.0)).thenReturn(102.0);

        var result = paymentService.addPaymentDetails(payment);

        assertEquals(1L, result);
        verify(paymentRepo).save(payment);
    }

    @Test
    void testUpdatePayment() {
        when(paymentRepo.findById(1L)).thenReturn(Optional.of(payment));
        when(payeeService.getPayeeById(1L)).thenReturn(payee);
        when(accountService.getPayeeAccountDetailsByName("John Doe")).thenReturn(toAccount);
        when(accountService.getAccountById(1L)).thenReturn(fromAccount);
        when(feeService.calculateFee(1L, 100.0)).thenReturn(102.0);

        var updatedPayment = new Payment();
        updatedPayment.setPaymentId(1L);
        updatedPayment.setAccountId(1L);
        updatedPayment.setPayeeId(1L);
        updatedPayment.setFeeId(1L);

        var result = paymentService.updatePayment(1L, updatedPayment);

        assertNotNull(result);
        assertEquals(1L, result.getPaymentId());
        verify(paymentRepo).save(payment);
    }

    @Test
    void testDeletePayment() {
        var result = paymentService.deletePayment(1L);

        assertEquals(true, result);
        verify(paymentRepo).deleteById(1L);
    }
}
