package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserServiceImpl userService;

    @Test
    public void getAllUsersControllerTest() throws Exception {

        List<UserInfo> userList = new ArrayList<>();
        UserInfo tempUser = new UserInfo(1, "Obama", "Barack", "mailBama@gmail.com");

        // GIVEN
        userList.add(tempUser);

        // WHEN
        Mockito.when(userService.findAll()).thenReturn(userList);

        // THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/Users").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$..firstName").value("Obama"))
                .andExpect(MockMvcResultMatchers.jsonPath("$..lastName").value("Barack"))
                .andExpect(status().isOk());

    }
}