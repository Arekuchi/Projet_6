package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DTO.BankAccountDTO;
import com.openclassroom.paymybuddy.model.BankAccount;

import java.util.List;

public interface IBankAccountService {

    BankAccount addBankAccount(String emailOwner, BankAccountDTO bankAccountDTO);
    Boolean deleteBankAccountByIban(String iban);

    List<BankAccount> findBankAccountByUser(String username);

    Boolean deleteBankAccount(String iban);


}
