package com.openclassroom.paymybuddy.services;



import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.model.User;

import java.math.BigDecimal;

public interface IPayMyBuddyServices {

    void makeInternalTransaction(User sender, User reveiver, BigDecimal amount);
    void makeExternalTransaction(User receiver, BigDecimal amount, String iban);
    void addFriend(UserInfo owner, UserInfo buddy);
    void deleteFriend(User owner, User buddy);

    void deleteUser(User user);

}
