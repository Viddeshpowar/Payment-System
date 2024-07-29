package com.payment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.payment.entity.Fee;
import com.payment.service.FeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FeeControllerTest {

    @InjectMocks
    private FeeController feeController;

    @Mock
    private FeeService feeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFee() {
        // Arrange
        Fee fee = new Fee();
        long feeId = 1L;
        when(feeService.addFee(fee)).thenReturn(feeId);

        // Act
        ResponseEntity<String> response = feeController.addFee(fee);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fee is saved Successfully & feeId is : " + feeId, response.getBody());
    }

    @Test
    void testGetFeeById() {
        // Arrange
        long feeId = 1L;
        Fee fee = new Fee();
        when(feeService.findFeeById(feeId)).thenReturn(fee);

        // Act
        ResponseEntity<Fee> response = feeController.getFeeById(feeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fee, response.getBody());
    }

    @Test
    void testCalculateFee() {
        // Arrange
        long feeId = 1L;
        double amount = 100.0;
        double calculatedFee = 10.0;
        when(feeService.calculateFee(feeId, amount)).thenReturn(calculatedFee);

        // Act
        ResponseEntity<Double> response = feeController.calculateFee(feeId, amount);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(calculatedFee, response.getBody());
    }
}
