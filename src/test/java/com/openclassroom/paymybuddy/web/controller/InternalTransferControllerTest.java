package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DTO.InternalTransferDTO;
import com.openclassroom.paymybuddy.model.Relation;
import com.openclassroom.paymybuddy.model.Role;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.services.ITransferService;
import com.openclassroom.paymybuddy.services.IUserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class InternalTransferControllerTest {

    @Autowired
    InternalTransferController internalTransferController;

    @Mock
    ITransferService transferService;

    @Mock
    IUserService userService;

    @Mock
    UserDetails userDetails;

    @Mock
    RedirectAttributes redirectAttributes;

    @Mock
    Model model;

    @Mock
    InternalTransferDTO internalTransferDTO;

    User owner = new User(1, "Barack", "Obama", "usa@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));
    User buddy = new User(2, "George", "Bush", "mail@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));

    Relation relation = new Relation(owner, buddy);

    Role role = new Role("USER");


    @Test
    void transferPage() {

        // GIVEN

        // WHEN
        when(userService.relationListEmail("email1")).thenReturn(Arrays.asList(new Relation()));
        when(transferService.findInternalTransferByUser("email1")).thenReturn(Arrays.asList(new InternalTransferDTO()));
        String result = internalTransferController.transferPage(model, userDetails);

        // THEN
        Assertions.assertThat(result).isEqualTo("intransfer");


    }

    @Test
    void doInternalTransfer() throws SQLException {

        // GIVEN

        // WHEN
        when(transferService.doInternalTransfer(internalTransferDTO)).thenReturn(new InternalTransferDTO());
        String result = internalTransferController.doInternalTransfer(internalTransferDTO, userDetails, redirectAttributes);

        // THEN
        Assertions.assertThat(result).isEqualTo("redirect:/user/intransfer");

    }
}