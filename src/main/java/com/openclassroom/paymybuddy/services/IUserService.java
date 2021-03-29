package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DTO.UserInfo;

import java.util.List;

public interface IUserService {

    List<UserInfo> findAll();
}
