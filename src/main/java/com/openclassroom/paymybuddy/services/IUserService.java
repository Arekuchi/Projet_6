package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.DTO.UserInfoCreate;
import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll();
    UserInfo findByEmail(String email);

    int countUsers();

    boolean addUser(UserInfoCreate userInfoCreate);
    boolean deleteUserByEmail(String email);
    boolean addRelation(User owner, User buddy);
    boolean deleteRelation(User owner, User buddy);


    User save(UserRegistrationDTO userRegistrationDTO);

}
