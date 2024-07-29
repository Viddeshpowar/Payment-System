package com.payment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.payment.dto.PayeeAccountDTO;
import com.payment.entity.Account;
import com.payment.entity.Payee;
import com.payment.repository.PayeeRepository;
import com.payment.service.AccountService;

public class PayeeServiceImplTest {

    @InjectMocks
    private PayeeServiceImpl payeeService;

    @Mock
    private PayeeRepository payeeRepo;

    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPayeeDetailsById() {
        // Arrange
        long payeeId = 1L;
        Payee payee = new Payee(123, "John Doe", 1000.0, Date.valueOf(LocalDate.now().plusDays(10)));
        Account account = new Account();  // Initialize with needed data
        PayeeAccountDTO payeeAccountDTO = new PayeeAccountDTO(payee, account);

        when(payeeRepo.findById(payeeId)).thenReturn(java.util.Optional.of(payee));
        when(accountService.getPayeeAccountDetailsByName(payee.getPayeeName())).thenReturn(account);

        // Act
        PayeeAccountDTO result = payeeService.getPayeeDetailsById(payeeId);

        // Assert
        assertEquals(payeeAccountDTO.getPayee(), result.getPayee());
        assertEquals(payeeAccountDTO.getToAccount(), result.getToAccount());
    }


    @Test
    void testGetPayeeById() {
        // Arrange
        long payeeId = 1L;
        Payee payee = new Payee(123, "John Doe", 1000.0, Date.valueOf(LocalDate.now().plusDays(10)));

        when(payeeRepo.findById(payeeId)).thenReturn(java.util.Optional.of(payee));

        // Act
        Payee result = payeeService.getPayeeById(payeeId);

        // Assert
        assertEquals(payee, result);
    }
}
