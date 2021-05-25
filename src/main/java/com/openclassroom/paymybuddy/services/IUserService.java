package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.DTO.UserInfoCreate;
import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.ExternalTransfer;
import com.openclassroom.paymybuddy.model.Relation;
import com.openclassroom.paymybuddy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll();
    UserInfo findByEmail(String email);

    int countUsers();

    boolean addUser(UserInfoCreate userInfoCreate);
    boolean deleteUserByEmail(String email);

    Relation addRelation(String emailOwner, String emailBuddy);
    boolean deleteRelation(User owner, User buddy);


    List<Relation> relationListEmail(String emailOwner);
    List<BankAccount> bankAccountListEmail(String emailOwner);
    List<ExternalTransfer> externalTransferListByIban(String iban);
//    List<ExternalTransfer> externalTransferListByEmail(String email);


    boolean deleteRelationById(Integer id);

    User save(UserRegistrationDTO userRegistrationDTO);


}
