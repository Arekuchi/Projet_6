package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBankAccountDAO extends JpaRepository<BankAccount, String> {

    BankAccount findBankAccountByIbanAndUser_Email(String ibanUser, String emailUser);
    BankAccount findBankAccountByIban(String iban);
    List<BankAccount> findBankAccountsByUser_Email(String email);

}
