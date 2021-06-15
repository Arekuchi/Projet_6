package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.model.Relation;
import com.openclassroom.paymybuddy.model.User;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class RelationControllerTest {

     @Autowired
     RelationController relationController;

    @Mock
    UserDetails userDetails;

    @Mock
    RedirectAttributes redirectAttributes;

    @Mock
    IUserService userService;

    @Mock
    Model model;

    User owner = new User(1, "Barack", "Obama", "usa@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));
    User buddy = new User(2, "George", "Bush", "mail@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));

    Relation relation = new Relation(owner, buddy);


    @Test
    void relationPage() {

        // GIVEN

        // WHEN
        when(userService.relationListEmail("email1")).thenReturn(Arrays.asList(new Relation()));
        String result = relationController.relationPage(model, userDetails);

        // THEN
        Assertions.assertThat(result).isEqualTo("relation");

    }

    @Test
    void addRelation() throws SQLException {

        // GIVEN

        // WHEN
        when(userService.addRelation(userDetails.getUsername(), buddy.getEmail())).thenReturn(new Relation());
        String result = relationController.addRelation(buddy.getEmail(), userDetails, redirectAttributes);

        // THEN
        Assertions.assertThat(result).isEqualTo("redirect:/user/relation");
    }

    @Test
    void deleteRelation() {

        // GIVEN

        // WHEN

        // THEN

    }
}