package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.model.Relation;
import com.openclassroom.paymybuddy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.SQLException;
import java.util.List;

public interface IUserService extends UserDetailsService {


    User save(UserRegistrationDTO userRegistrationDTO) throws SQLException;
    boolean deleteRelationById(Integer id);
    List<Relation> relationListEmail(String emailOwner);
    Relation addRelation(String emailOwner, String emailBuddy) throws SQLException;




}
