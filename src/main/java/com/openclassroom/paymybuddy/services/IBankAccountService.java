package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DTO.BankAccountDTO;
import com.openclassroom.paymybuddy.DTO.BankAccountInfo;
import com.openclassroom.paymybuddy.model.BankAccount;

import java.util.List;

public interface IBankAccountService {

    BankAccount addBankAccount(String emailOwner, BankAccountDTO bankAccountDTO);
    Boolean deleteBankAccountByIban(String iban);

    List<BankAccount> findBankAccountByUser(String username);












    List<BankAccountInfo> findAll();
    BankAccountInfo findByIban(String iban);
    List<BankAccountInfo> findByBankName(String bankName);

    BankAccountInfo findByEmail(String email);
}
