package com.payment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;

import com.payment.dto.PayeeAccountDTO;
import com.payment.entity.Account;
import com.payment.entity.Payee;
import com.payment.service.AccountService;
import com.payment.service.PayeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PayeeControllerTest {

    @InjectMocks
    private PayeeController payeeController;

    @Mock
    private PayeeService payeeService;

    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPayee_ValidDate() {
        // Arrange
        Payee payee = new Payee();
        // Set up the payee with a due date that is in the future
        payee.setDueDate(Date.valueOf(LocalDate.now().plusDays(1)));
        
        Account toAccount = new Account();
        PayeeAccountDTO payeeAccountDTO = new PayeeAccountDTO(payee, toAccount);
        
        long payeeId = 1L;
        long accountId = 2L;
        
        when(payeeService.addPayee(payee)).thenReturn(payeeId);
        when(accountService.addAccount(toAccount)).thenReturn(accountId);

        // Act
        ResponseEntity<String> response = payeeController.addPayee(payeeAccountDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Payee details saved successfully. Payee ID is: " + payeeId + " Payee's Account Id is :" + accountId, response.getBody());
    }

    @Test
    void testAddPayee_InvalidDate() {
        // Arrange
        Payee payee = new Payee();
        // Set up the payee with a due date that is in the past
        payee.setDueDate(Date.valueOf(LocalDate.now().minusDays(1)));
        
        Account toAccount = new Account();
        PayeeAccountDTO payeeAccountDTO = new PayeeAccountDTO(payee, toAccount);

        // Act
        ResponseEntity<String> response = payeeController.addPayee(payeeAccountDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Due date must be a present or future date", response.getBody());
    }

    @Test
    void testGetPayee() {
        // Arrange
        long payeeId = 1L;
        Payee payee = new Payee();
        Account account = new Account();
        PayeeAccountDTO payeeAccountDTO = new PayeeAccountDTO(payee, account);
        
        when(payeeService.getPayeeDetailsById(payeeId)).thenReturn(payeeAccountDTO);

        // Act
        ResponseEntity<PayeeAccountDTO> response = payeeController.getPayee(payeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payeeAccountDTO, response.getBody());
    }
}
