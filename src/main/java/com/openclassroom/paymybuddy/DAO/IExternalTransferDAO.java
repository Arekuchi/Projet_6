package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.ExternalTransfer;
import com.openclassroom.paymybuddy.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExternalTransferDAO extends JpaRepository<ExternalTransfer, Integer> {

    List<ExternalTransfer> findAllByBankAccountIban(String iban);


//    List<ExternalTransfer> findAllByUserEmail(List<BankAccount> bankAccountList);

}
