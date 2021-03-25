package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.PaymybuddyApplication;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    IUserDAO userDAO;
    @Autowired
    UserServiceImpl userService;

    // appel aux services de la classe  /UserServiceImpl/

    // POST - CREATE
    @PostMapping("/Users")
    public void getUser(@RequestBody User user) throws Exception {
        userService.CreateUser(user);

    }


    // GET - READ

    @GetMapping("/Users")
    public void getUsers(@RequestBody User user) throws Exception {

        userService.findAll();
    }

    @GetMapping("/Users/{email}")
    public void getUserByEmail(@RequestBody User user, @PathVariable String email) throws Exception {


    }

    @GetMapping("/Users/Count")
    public int getUserCount() throws Exception {
        List<User> getAllUser = new ArrayList<>(userDAO.findAll());

        return getAllUser.size();
    }

    // UPDATE
    @PutMapping("/Users/{userId}")
    public void updateUserById(@RequestBody User user, @PathVariable Integer userId) throws Exception {


    }

    // DELETE

    @DeleteMapping("/Users/{userId}")
    public void deleteUserById(@RequestBody User user, @PathVariable Integer userId) throws Exception {

    }

}
