package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.ExternalTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExternalTransferDAO extends JpaRepository<ExternalTransfer, Integer> {


}
