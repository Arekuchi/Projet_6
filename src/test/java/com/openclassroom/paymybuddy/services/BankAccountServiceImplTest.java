package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IBankAccountDAO;
import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.BankAccountDTO;
import com.openclassroom.paymybuddy.model.*;
import com.openclassroom.paymybuddy.web.exception.DataAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BankAccountServiceImplTest {

     @Autowired
     IBankAccountService bankAccountService;

     @MockBean
    IBankAccountDAO bankAccountDAO;

    @MockBean
    IUserDAO userDAO;

    User owner = new User(1, "Barack", "Obama", "usa@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));
    User buddy = new User(2, "George", "Bush", "mail@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));

    BankAccount bankAccount = new BankAccount("iban_obama", "bic_ obama", "CIC", "Compte courant", owner, Arrays.asList(new ExternalTransfer()));

    Relation relation = new Relation(owner, buddy);

    Role role = new Role("USER");

    BankAccountDTO bankAccountDTO = new BankAccountDTO("ibanTest", "bicTest", "BankNameTest", "accountTest");

    @Test
    void addBankAccount() throws SQLException {

        //GIVEN
        try {
            bankAccountService.addBankAccount("email1", bankAccountDTO);
            verify(userDAO, times(1)).findByEmail(any());
            verify(bankAccountDAO, times(1)).findBankAccountByIban(any());
        } catch (DataAlreadyExistsException e) {
            assert (e.getMessage().contains("Cet IBAN est déjà utilisé"));
        }

    }

    @Test
    void deleteBankAccountByIban() {

        // GIVEN

        // WHEN
        when(bankAccountDAO.existsById(any())).thenReturn(true);
        bankAccountService.deleteBankAccountByIban(bankAccountDTO.getIban());
        verify(bankAccountDAO, times(1)).deleteById(any());

        // THEN
    }

    @Test
    void findBankAccountByUser() {

        // GIVEN

        // WHEN
        when(bankAccountDAO.findBankAccountsByUser_Email(any())).thenReturn(Arrays.asList(bankAccount));
        bankAccountService.findBankAccountByUser(any());
        verify(bankAccountDAO, times(1)).findBankAccountsByUser_Email(any());

        // THEN
    }

    @Test
    void deleteBankAccount() {

        // GIVEN

        // WHEN
        when(bankAccountDAO.existsById(any())).thenReturn(true);
        bankAccountService.deleteBankAccount(bankAccount.getIban());
        verify(bankAccountDAO, times(1)).deleteById(any());

        // THEN
    }
}