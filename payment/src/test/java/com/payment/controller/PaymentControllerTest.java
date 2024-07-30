package com.payment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.Arrays;
import java.util.List;

import com.payment.dto.PaymentDetailsDTO;
import com.payment.entity.Payment;
import com.payment.service.PaymentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPayment() {
        // Arrange
        PaymentDetailsDTO payment1 = new PaymentDetailsDTO(); // Populate as needed
        PaymentDetailsDTO payment2 = new PaymentDetailsDTO(); // Populate as needed
        List<PaymentDetailsDTO> paymentList = Arrays.asList(payment1, payment2);
        when(paymentService.getAllPayments()).thenReturn(paymentList);

        // Act
        ResponseEntity<List<PaymentDetailsDTO>> response = paymentController.getAllPayment();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paymentList, response.getBody());
    }

    @Test
    void testGetPaymentById() {
        // Arrange
        long paymentId = 1L;
        PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO(); // Populate as needed
        when(paymentService.getPaymentById(paymentId)).thenReturn(paymentDetailsDTO);

        // Act
        ResponseEntity<PaymentDetailsDTO> response = paymentController.getPaymentById(paymentId);

        // Assert
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(paymentDetailsDTO, response.getBody());
    }


//    @Test
//    void testUpdatePayment() {
//        // Arrange
//        long paymentId = 1L;
//        Payment payment = new Payment(); // Populate as needed
//        Payment updatedPayment = new Payment(); // Populate as needed
//        when(paymentService.updatePayment(paymentId, payment)).thenReturn(updatedPayment);
//
//        // Act
//        ResponseEntity<Payment> response = paymentController.updatePayment(paymentId, payment);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(updatedPayment, response.getBody());
//    }

    @Test
    void testDeletePayment_Success() {
        // Arrange
        long paymentId = 1L;
        doNothing().when(paymentService).deletePayment(paymentId); // Use doNothing() for void methods
        when(paymentService.getPaymentById(paymentId)).thenReturn(null); // Simulate that payment was deleted

        // Act
        ResponseEntity<String> response = paymentController.deletePayment(paymentId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("payment is deleted", response.getBody());
    }

    @Test
    void testDeletePayment_Failure() {
        // Arrange
        long paymentId = 1L;
        doThrow(new RuntimeException("Error")).when(paymentService).deletePayment(paymentId);

        // Act
        ResponseEntity<String> response = paymentController.deletePayment(paymentId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while deleting the payment: Error", response.getBody());
    }
}
