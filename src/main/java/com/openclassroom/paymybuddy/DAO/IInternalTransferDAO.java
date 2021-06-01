package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.InternalTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInternalTransferDAO extends JpaRepository<InternalTransfer, Integer> {

    List<InternalTransfer> findAllBySenderID_EmailOrderByTransactionDateDesc(String emailOwner);

}
