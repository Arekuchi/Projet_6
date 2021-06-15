package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DTO.BankAccountDTO;
import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.Relation;
import com.openclassroom.paymybuddy.model.Role;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.services.IBankAccountService;
import com.openclassroom.paymybuddy.services.ITransferService;
import com.openclassroom.paymybuddy.services.IUserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.when;


class ExternalTransferControllerTest {

    @Autowired
    ExternalTransferController externalTransferController;

    @Mock
    ITransferService transferService;

    @Mock
    IBankAccountService bankAccountService;

    @Mock
    IUserService userService;

    @Mock
    UserDetails userDetails;

    @Mock
    RedirectAttributes redirectAttributes;

    @Mock
    Model model;

    @Mock
    ExternalTransferDTO externalTransferDTO;

    @Mock
    BankAccountDTO bankAccountDTO;

    User owner = new User(1, "Barack", "Obama", "usa@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));
    User buddy = new User(2, "George", "Bush", "mail@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));

    Relation relation = new Relation(owner, buddy);

    Role role = new Role("USER");




    @Test
    void externalTransferPage() {

        // GIVEN

        // WHEN
        when(transferService.findExternalTransferByUser("email1")).thenReturn(Arrays.asList(new ExternalTransferDTO()));
        when((bankAccountService.findBankAccountByUser("email1"))).thenReturn(Arrays.asList(new BankAccount()));
        String result = externalTransferController.externalTransferPage(model, userDetails);



        // THEN
        Assertions.assertThat(result).isEqualTo("extransfer");


    }

    @Test
    void addBankAccount() throws SQLException {

        // GIVEN
        when(bankAccountService.addBankAccount("mailTest", bankAccountDTO)).thenReturn(new BankAccount());
        String result = externalTransferController.addBankAccount(bankAccountDTO, userDetails, redirectAttributes);

        // WHEN

        // THEN
        Assertions.assertThat(result).isEqualTo("redirect:/user/extransfer");

    }

    @Test
    void deleteBankAccount() {
    }

    @Test
    void doExternalTransfer() {
    }
}