package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBankAccountDAO extends JpaRepository<BankAccount, String> {


    BankAccount findByIban(String iban);
    List<BankAccount> findByBankName(String bankName);
    BankAccount findByUserEmail(String email);

}
