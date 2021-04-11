package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.Relation;
import com.openclassroom.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRelationDAO extends JpaRepository<Relation, Integer> {

    Relation findByBuddy(User buddy);

}
