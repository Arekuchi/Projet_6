package com.openclassroom.paymybuddy.services;



import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.model.Transfer;
import com.openclassroom.paymybuddy.model.User;

import java.math.BigDecimal;

public interface IPayMyBuddyServices {

    Transfer makeTransaction(BigDecimal amount, String description);
    void makeInternalTransaction(User sender, User receiver, BigDecimal amount, String description);
    void makeExternalTransaction(User receiver, BigDecimal amount, String iban, String description);
    void addFriend(UserInfo owner, UserInfo buddy);
    void deleteFriend(User owner, User buddy);

    void deleteUser(User user);

}
