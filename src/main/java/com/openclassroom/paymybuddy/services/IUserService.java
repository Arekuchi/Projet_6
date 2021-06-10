package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.model.Relation;
import com.openclassroom.paymybuddy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {


    User save(UserRegistrationDTO userRegistrationDTO);
    boolean deleteRelationById(Integer id);
    List<Relation> relationListEmail(String emailOwner);
    Relation addRelation(String emailOwner, String emailBuddy);




}
