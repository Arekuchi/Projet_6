package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.services.BankAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {

    // appel au service addBankAccount du service /BankAccountServiceImpl/
    @Autowired
    BankAccountServiceImpl bankAccountService;


    // POST - CREATE
    @PostMapping("/AddAccount/{userId}")
    public void addBankAccount(@RequestBody BankAccount bankAccount, @PathVariable Integer userId) throws Exception {


    }

    // GET - READ
    @GetMapping("/BankAccount")
    public void getBankAccounts(@RequestBody BankAccount bankAccount) throws Exception {

    }

    @GetMapping("/BankAccount/{email}")
    public void getBankAccountByUserId(@RequestBody BankAccount bankAccount, @PathVariable String email) throws Exception {

    }

    // UPDATE

    // DELETE
}
