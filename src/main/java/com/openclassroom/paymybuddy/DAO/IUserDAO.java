package com.openclassroom.paymybuddy.DAO;

import com.openclassroom.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IUserDAO extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    Boolean existsByEmail(String email);


}
