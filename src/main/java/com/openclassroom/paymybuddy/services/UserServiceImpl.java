package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements IUserService {


    @Autowired
    IUserDAO userDAO;


    @Override
    public List<UserInfo> findAll() {
        List<User> userList = userDAO.findAll();
        List<UserInfo> userInfoList = new ArrayList<>();

        for (User user : userList) {
            UserInfo userInfo = new UserInfo();
            userInfo.setFirstName(user.getFirstName());
            userInfo.setLastName(user.getLastName());
            userInfo.setEmail(user.getEmail());
            userInfo.setId(user.getId());
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }


    public List<UserInfo> findByEmail(String email) {
        List<User> userList = userDAO.findAll();
        List<UserInfo> userInfoList = new ArrayList<>();

//        if (email.isEmpty()) {
//            throw new InvalidArgumentException("L'email ne peut pas être vide");
//        }
        for (User user : userList) {
            UserInfo userInfo = new UserInfo();
            if (user.getEmail().equalsIgnoreCase(email)) {
                userInfo.setFirstName(user.getFirstName());
                userInfo.setLastName(user.getLastName());
                userInfo.setEmail(user.getEmail());
            }

        }
        return userInfoList;
    }

}
