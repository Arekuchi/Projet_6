package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransferDAO extends JpaRepository<Transfer, Integer> {

    List<Transfer> findByStatus(String status);

}
