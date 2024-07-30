package com.payment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.payment.entity.Account;
import com.payment.entity.Fee;
import com.payment.entity.Payee;
import com.payment.entity.Payment;
import com.payment.repository.PaymentRepository;
import com.payment.service.AccountService;
import com.payment.service.FeeService;
import com.payment.service.PayeeService;

public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepo;

    @Mock
    private PayeeService payeeService;

    @Mock
    private AccountService accountService;

    @Mock
    private FeeService feeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testGetPaymentById() {
//        // Arrange
//        long paymentId = 1L;
//        Payment payment = new Payment(100L, 200L, 300L);
//        payment.setPaymentId(paymentId);
//
//        Account fromAccount = new Account();
//        Payee payee = new Payee();
//        Account toAccount = new Account();
//        Fee fee = new Fee();
//
//        PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO(
//            paymentId,
//            fromAccount,
//            payee,
//            toAccount,
//            fee,
//            payment.getUpdatedDatetime()
//        );
//
//        when(paymentRepo.findById(paymentId)).thenReturn(Optional.of(payment));
//        when(accountService.getAccountById(payment.getAccountId())).thenReturn(fromAccount);
//        when(payeeService.getPayeeById(payment.getPayeeId())).thenReturn(payee);
//        when(accountService.getPayeeAccountDetailsByName(payee.getPayeeName())).thenReturn(toAccount);
//        when(feeService.findFeeById(payment.getFeeId())).thenReturn(fee);
//
//        // Act
//        PaymentDetailsDTO result = paymentService.getPaymentById(paymentId);
//
//        // Assert
//        assertEquals(paymentDetailsDTO, result);
//    }

//    @Test
//    void testAddPayment() {
//        // Arrange
//        Payment payment = new Payment(100L, 200L, 300L);
//        payment.setPaymentId(1L);
//
//        when(paymentRepo.save(payment)).thenReturn(payment);
//
//        // Act
//        long result = paymentService.addPayment(payment);
//
//        // Assert
//        assertEquals(payment.getPaymentId(), result);
//    }

    @Test
    void testAddPaymentDetails() {
        // Arrange
        Payment payment = new Payment(100L, 200L, 300L);
        payment.setPaymentId(1L);

        Payee payee = new Payee();
        payee.setPayeeName("Test Payee");
        payee.setAmountDue(1000.0);

        Account toAccount = new Account();
        toAccount.setAccountBalance(5000.0);

        Account fromAccount = new Account();
        fromAccount.setAccountBalance(2000.0);

        Fee fee = new Fee();
        when(feeService.calculateFee(payment.getFeeId(), payee.getAmountDue())).thenReturn(50.0);

        when(payeeService.getPayeeById(payment.getPayeeId())).thenReturn(payee);
        when(accountService.getPayeeAccountDetailsByName(payee.getPayeeName())).thenReturn(toAccount);
        when(accountService.getAccountById(payment.getAccountId())).thenReturn(fromAccount);

        when(paymentRepo.save(payment)).thenReturn(payment);

        // Act
        long result = paymentService.addPaymentDetails(payment);

        // Assert
        assertEquals(payment.getPaymentId(), result);
        assertEquals(5050.0, toAccount.getAccountBalance());
        assertEquals(1950.0, fromAccount.getAccountBalance());
        assertEquals(0.0, payee.getAmountDue());
    }

//    @Test
//    void testUpdatePayment() {
//        // Arrange
//        long paymentId = 1L;
//        Payment payment = new Payment(100L, 200L, 300L);
//        payment.setPaymentId(paymentId);
//
//        when(paymentRepo.findById(paymentId)).thenReturn(Optional.of(payment));
//        when(paymentRepo.save(payment)).thenReturn(payment);
//
//        // Act
//        Payment result = paymentService.updatePayment(paymentId, payment);
//
//        // Assert
//        assertEquals(payment, result);
//    }

    @Test
    void testDeletePayment() {
        // Arrange
        long paymentId = 1L;

        // Act
        boolean result = paymentService.deletePayment(paymentId);

        // Assert
        verify(paymentRepo, times(1)).deleteById(paymentId);
        assertEquals(true, result);
    }
}
