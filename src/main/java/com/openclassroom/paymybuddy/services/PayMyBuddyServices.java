package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class PayMyBuddyServices implements IPayMyBuddyServices {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IUserService userService;

    /**
     * Method to make an Internal Transfer
     * Checking if Users exists, then if the sender's balance is enough to make the transfer
     * @param sender
     * @param reveiver
     * @param amount
     */
    @Override
    public void makeInternalTransaction(User sender, User reveiver, BigDecimal amount) {

        // init comme add friend + amount > user.getAmount

        // save

    }

    /**
     * Mehtod to make an External Transfer
     * Checking if User exists, and that the fees calculation is good
     * @param receiver
     * @param amount
     * @param iban
     */
    @Override
    public void makeExternalTransaction(User receiver, BigDecimal amount, String iban) {

    }

    /**
     * Method used to add a user as friend.
     * Checking if both User exists in database.
     * Then we add the relation in the table database "relation".
     * @param owner
     * @param buddy
     */
    @Override
    public void addFriend(User owner, User buddy) {

        User userOwner = userDAO.findByEmail(owner.getEmail()); // userService a besoin de UserInfo
        User userBuddy = userDAO.findByEmail(buddy.getEmail());

        if (userOwner == null) {
            throw new DataNotFoundException("L'utilisateur n'existe pas : " + owner.getFirstName() + owner.getLastName());
        }
        if (userBuddy == null) {
            throw new DataNotFoundException("L'utilisateur n'existe pas : " + buddy.getFirstName() + buddy.getLastName());
        }

        userService.addRelation(owner, buddy);

    }


    @Override
    public void deleteFriend(User owner, User buddy) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
