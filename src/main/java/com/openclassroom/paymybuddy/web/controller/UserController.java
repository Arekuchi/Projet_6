package com.openclassroom.paymybuddy.web.controller;



import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    // appel aux services de la classe  /UserServiceImpl/

    @Autowired
    UserServiceImpl userService;


    // POST - CREATE

    @PostMapping("/Users")
    public void getUser(@RequestBody User user) throws Exception {

    }


    // GET - READ

    @GetMapping("/Users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserInfo> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/Users/{email}")
    public List<UserInfo> getUserByEmail(@PathVariable String email) throws Exception {

        return userService.findByEmail(email);


    }

    @GetMapping("/Users/Count")
    public int getUserCount() throws Exception {


        return 0;
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
