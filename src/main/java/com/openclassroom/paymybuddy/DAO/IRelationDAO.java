package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelationDAO extends JpaRepository<Relation, Integer> {


    Relation findByOwner_EmailAndBuddy_Email(String senderEmail, String receiverEmail);

    List<Relation> findAllByOwner_Email(String emailOwner);

}
