package com.payment.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.payment.entity.Account;
import com.payment.service.AccountService;

public class AccountControllerTest{

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAccount() {
        // Arrange
        Account account = new Account();
        long accountId = 1L;
        when(accountService.addAccount(account)).thenReturn(accountId);

        // Act
        ResponseEntity<String> response = accountController.addAccount(account);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Account saved Succssfully Please not AccountId : " + accountId, response.getBody());
    }
}
