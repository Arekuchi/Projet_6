package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.services.IUserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;


@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRegistrationController userRegistrationController;

    @MockBean
    IUserService userService;

    @Mock
    UserRegistrationDTO userRegistrationDTO;


    @Test
    void showRegistrationForm() {

        // GIVEN

        // WHEN
        String result = userRegistrationController.showRegistrationForm();

        // THEN
        Assertions.assertThat(result).isEqualTo("registration");

    }

    @Test
    void userRegistrationDTO() throws SQLException {

        // GIVEN

        // WHEN

        // THEN



    }

    @Test
    void registerUserAccount() throws SQLException {

        // GIVEN

        // WHEN
        String result = userRegistrationController.registerUserAccount(userRegistrationDTO);

        // THEN
        Assertions.assertThat(result).isEqualTo("redirect:/registration?success");

    }
}