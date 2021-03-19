package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.model.BankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {

    @PostMapping("/AddAccount/{userId}")
    public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankAccount, @PathVariable Integer userId) throws Exception {

        // appel au service addBankAccount du service /BankAccountServiceImpl/
    } 

}
