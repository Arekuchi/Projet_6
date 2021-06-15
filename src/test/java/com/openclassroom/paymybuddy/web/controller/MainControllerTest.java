package com.openclassroom.paymybuddy.web.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    MainController mainController;


    @Test
    void login() {

        // GIVEN

        // WHEN
        String result = mainController.login();

        // THEN
        Assertions.assertThat(result).isEqualTo("login");


    }

    @Test
    void home() {

        // GIVEN

        // WHEN
        String result = mainController.home();

        // THEN
        Assertions.assertThat(result).isEqualTo("index");
    }
}