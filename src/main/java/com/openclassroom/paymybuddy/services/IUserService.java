package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.DTO.UserInfoCreate;

import java.util.List;

public interface IUserService {

    List<UserInfo> findAll();
    UserInfo findByEmail(String email);
    int countUsers();

    Boolean addUser(UserInfoCreate userInfoCreate);
}
