package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.DTO.UserInfoCreate;
import com.openclassroom.paymybuddy.model.User;

import java.util.List;

public interface IUserService {

    List<UserInfo> findAll();
    UserInfo findByEmail(String email);

    int countUsers();

    Boolean addUser(UserInfoCreate userInfoCreate);
    Boolean addRelation(User owner, User buddy);
}
