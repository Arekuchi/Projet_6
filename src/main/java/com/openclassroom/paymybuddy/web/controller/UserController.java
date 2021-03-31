package com.openclassroom.paymybuddy.web.controller;



import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.DTO.UserInfoCreate;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api")
public class UserController {

    // appel aux services de la classe  /UserServiceImpl/
    @Autowired
    UserServiceImpl userService;

    // Logger log4j
    private final Logger logger = LogManager.getLogger(this.getClass().getName());


    // POST - CREATE

    @PostMapping("/AddUser")
    public ResponseEntity<User> addUser(@RequestBody UserInfoCreate userInfoCreate) throws Exception {
        logger.info("Beginning inside addUser in UserController" + userInfoCreate.toString());

        userService.addUser(userInfoCreate);
        logger.info("AddUser for " + userInfoCreate.toString() + "IS OK ! ");

        return new ResponseEntity(userInfoCreate, HttpStatus.CREATED);

    }


    // GET - READ

    @GetMapping("/Users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserInfo> getUsers() {
        return userService.findAll();
    }


    @GetMapping("/Users/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfo getUserByEmail(@PathVariable String email)  {

        return userService.findByEmail(email);


    }

    @GetMapping("/UsersCount")
    @ResponseStatus(HttpStatus.OK)
    public Integer getUserCount() throws Exception {

        return userService.countUsers();
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
