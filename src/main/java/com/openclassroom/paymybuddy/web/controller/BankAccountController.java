package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DTO.BankAccountInfo;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.services.BankAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/BankAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccountInfo> getBankAccounts() throws Exception {
        return bankAccountService.findAll();
    }

    @GetMapping("/BankAccount/UserEmail/{email}")
    @ResponseStatus(HttpStatus.OK)
    public BankAccountInfo getBankAccountByUserEmail(@PathVariable String email) throws Exception {

        return bankAccountService.findByEmail(email);
    }

    @GetMapping("/BankAccount/iban/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public BankAccountInfo getBankAccountByIban(@PathVariable String iban) throws Exception {

        return bankAccountService.findByIban(iban);
    }


    @GetMapping("/BankAccounts/{bankName}")
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccountInfo> getBankAccountsByBankName(@PathVariable String bankName) {

        return bankAccountService.findByBankName(bankName);
    }

    // UPDATE

    // DELETE
}
