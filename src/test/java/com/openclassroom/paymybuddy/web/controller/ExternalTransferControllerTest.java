package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DAO.IBankAccountDAO;
import com.openclassroom.paymybuddy.DTO.BankAccountDTO;
import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.model.*;
import com.openclassroom.paymybuddy.services.IBankAccountService;
import com.openclassroom.paymybuddy.services.ITransferService;
import com.openclassroom.paymybuddy.services.IUserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
class ExternalTransferControllerTest {

    @Autowired
    ExternalTransferController externalTransferController;

    @Mock
    ITransferService transferService;

    @Mock
    IUserService userService;

    @Mock
    IBankAccountService bankAccountService;

    @Mock
    UserDetails userDetails;

    @Mock
    RedirectAttributes redirectAttributes;

    @Mock
    Model model;

    @Mock
    ExternalTransferDTO externalTransferDTO;


    User owner = new User(1, "Barack", "Obama", "usa@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));
    User buddy = new User(2, "George", "Bush", "mail@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));

    BankAccountDTO bankAccount = new BankAccountDTO("iban_obama", "bic_ obama", "CIC", "Compte courant");

    Relation relation = new Relation(owner, buddy);

    Role role = new Role("USER");

    @Test
    void externalTransferPage() {

        // GIVEN

        // WHEN
        when(transferService.findExternalTransferByUser("email1")).thenReturn(Arrays.asList(new ExternalTransferDTO()));
        when(bankAccountService.findBankAccountByUser("email1")).thenReturn(Arrays.asList(new BankAccount()));
        String result = externalTransferController.externalTransferPage(model, userDetails);

        // THEN
        Assertions.assertThat(result).isEqualTo("extransfer");
    }

    @Test
    void addBankAccount() throws SQLException {

        // GIVEN

        // WHEN
        when(bankAccountService.addBankAccount("email1", bankAccount)).thenReturn(new BankAccount());
        String result = externalTransferController.addBankAccount(bankAccount, userDetails, redirectAttributes);

        // THEN
        Assertions.assertThat(result).isEqualTo("redirect:/user/extransfer");

    }


    @Test
    void doExternalTransfer() throws SQLException {

//        // GIVEN
//
//        // WHEN
//        when(transferService.doExternalTransfer(externalTransferDTO)).thenReturn(new ExternalTransferDTO());
//        String result = externalTransferController.doExternalTransfer(externalTransferDTO, userDetails);
//
//        // THEN
//        Assertions.assertThat(result).isEqualTo("redirect:/user/extransfer");
    }
}