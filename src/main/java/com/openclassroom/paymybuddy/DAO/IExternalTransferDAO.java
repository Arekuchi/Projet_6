package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.ExternalTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExternalTransferDAO extends JpaRepository<ExternalTransfer, Integer> {

    List<ExternalTransfer> findAllByBankAccount_User_EmailOrderByTransactionDateDesc(String emailOwner);


}
