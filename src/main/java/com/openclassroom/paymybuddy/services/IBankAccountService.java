package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DTO.BankAccountInfo;

import java.util.List;

public interface IBankAccountService {

    List<BankAccountInfo> findAll();
    BankAccountInfo findByIban(String iban);
    List<BankAccountInfo> findByBankName(String bankName);
    BankAccountInfo findByEmail(String email);
}
