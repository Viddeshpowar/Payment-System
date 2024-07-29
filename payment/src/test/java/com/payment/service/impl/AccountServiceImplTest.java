package com.payment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import com.payment.entity.Account;
import com.payment.repository.AccountRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Mock
    private AccountRepository accRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccountById() {
        // Arrange
        long accountId = 1L;
        Account account = new Account(); // Populate with necessary fields if required
        // Assume `account` is created with the necessary properties
        when(accRepo.findById(accountId)).thenReturn(Optional.of(account));

        // Act
        Account result = accountServiceImpl.getAccountById(accountId);

        // Assert
        assertEquals(account, result);
    }

    @Test
    void testAddAccount() {
        // Arrange
        Account account = new Account(); // Populate with necessary fields if required
        long expectedAccountId = 1L;
        when(accRepo.findMaxAccountId()).thenReturn(expectedAccountId);

        // Act
        long result = accountServiceImpl.addAccount(account);

        // Assert
        verify(accRepo).save(account); // Verify that save method was called
        assertEquals(expectedAccountId, result);
    }

    @Test
    void testGetPayeeAccountDetailsByName() {
        // Arrange
        String payeeName = "John Doe";
        Account account = new Account(); // Populate with necessary fields if required
        when(accRepo.findByAccountName(payeeName)).thenReturn(List.of(account));

        // Act
        Account result = accountServiceImpl.getPayeeAccountDetailsByName(payeeName);

        // Assert
        assertEquals(account, result);
    }

    @Test
    void testGetMaxAccount() {
        // Arrange
        long expectedMaxAccountId = 1L;
        when(accRepo.findMaxAccountId()).thenReturn(expectedMaxAccountId);

        // Act
        long result = accountServiceImpl.getMaxAccount();

        // Assert
        assertEquals(expectedMaxAccountId, result);
    }
}
