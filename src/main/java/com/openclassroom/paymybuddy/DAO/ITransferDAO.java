package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransferDAO extends JpaRepository<Transfer, Integer> {

    Transfer findByStatus(String status);
}
