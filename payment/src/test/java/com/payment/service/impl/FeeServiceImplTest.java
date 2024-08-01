package com.payment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.payment.entity.Fee;
import com.payment.repository.FeeRepository;

public class FeeServiceImplTest {

    @InjectMocks
    private FeeServiceImpl feeServiceImpl;

    @Mock
    private FeeRepository feeRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateFee() {
        // Arrange
        long feeId = 1L;
        double amount = 500.0;
        Fee fee = new Fee();
        fee.setAmountMax(Arrays.asList(100L, 500L, 1000L));
        fee.setAmountMin(Arrays.asList(0L, 100L, 500L));
        fee.setFeeAmount(Arrays.asList(10L, 20L, 30L));
        when(feeRepo.findById(feeId)).thenReturn(Optional.of(fee));

        // Act
        double result = feeServiceImpl.calculateFee(feeId, amount);

        // Assert
        assertEquals(30.0 + amount, result); // 30L is the fee amount for the range 500L - 1000L
    }

    @Test
    void testAddFee() {
        // Arrange
        Fee fee = new Fee();
        long feeId = 1L;
        when(feeRepo.findMaxFeeId()).thenReturn(feeId);

        // Act
        long result = feeServiceImpl.addFee(fee);

        // Assert
        verify(feeRepo).save(fee); // Verify that save method was called
        assertEquals(feeId, result);
    }

    @Test
    void testGetRecentFeeId() {
        // Arrange
        long expectedFeeId = 1L;
        when(feeRepo.findMaxFeeId()).thenReturn(expectedFeeId);

        // Act
        long result = feeServiceImpl.getRecentFeeId();

        // Assert
        assertEquals(expectedFeeId, result);
    }

    @Test
    void testFindFeeById() {
        // Arrange
        long feeId = 1L;
        Fee fee = new Fee(); // Populate with necessary fields if required
        when(feeRepo.findById(feeId)).thenReturn(Optional.of(fee));

        // Act
        Fee result = feeServiceImpl.findFeeById(feeId);

        // Assert
        assertEquals(fee, result);
    }

    @Test
    void testGetRecentFee() {
        // Arrange
        long feeId = 1L;
        Fee fee = new Fee(); // Populate with necessary fields if required
        when(feeRepo.findMaxFeeId()).thenReturn(feeId);
        when(feeRepo.findById(feeId)).thenReturn(Optional.of(fee));

        // Act
        Fee result = feeServiceImpl.getRecentFee();

        // Assert
        assertEquals(fee, result);
    }
}
