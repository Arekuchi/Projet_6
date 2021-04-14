package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.InternalTransferInfo;
import com.openclassroom.paymybuddy.DTO.TransferInfo;
import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.model.ExternalTransfer;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import com.openclassroom.paymybuddy.web.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class PayMyBuddyServices implements IPayMyBuddyServices {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IUserService userService;

    @Autowired
    ITransferService transferService;

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
        User userSender = userDAO.findByEmail(sender.getEmail());
        User userReceiver = userDAO.findByEmail(sender.getEmail());
        double tempAmount = amount.doubleValue();
        double tempBalance = userSender.getBalance().doubleValue();

        if (userSender.getEmail() == null) {
            throw new DataNotFoundException("L'utilisateur n'existe pas : " + userSender.getFirstName() + userSender.getLastName());
        }
        if (userReceiver.getEmail() == null) {
            throw new DataNotFoundException("L'utilisateur n'existe pas : " + userReceiver.getFirstName() + userReceiver.getLastName());
        }
        if (tempAmount < tempBalance) {
            throw new InvalidArgumentException("Le montant du compte est inférieur au montant du versement");
        }

        InternalTransferInfo internalTransferInfo = new InternalTransferInfo();
        internalTransferInfo.setSenderId(sender.getId());
        internalTransferInfo.setReceiverId(reveiver.getId());

        // save
        transferService.addInternalTransaction(internalTransferInfo);
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

        User userReceiver = userDAO.findByEmail(receiver.getEmail());

        if (userReceiver.getEmail() == null) {
            throw new DataNotFoundException("L'utilisateur n'existe pas : " + userReceiver.getFirstName() + userReceiver.getLastName());
        }

        ExternalTransferDTO tempExternalTransfer = new ExternalTransferDTO();
        tempExternalTransfer.setAmount(amount);
        tempExternalTransfer.setUser(receiver);
        tempExternalTransfer.setIban(iban);
        transferService.addExternalTransaction(tempExternalTransfer);
    }

    /**
     * Method used to add a user as friend.
     * Checking if both User exists in database.
     * Then we add the relation in the table database "relation".
     * @param owner
     * @param buddy
     */
    @Override
    public void addFriend(UserInfo owner, UserInfo buddy) {

        UserInfo userOwner = userService.findByEmail(owner.getEmail()); // userService a besoin de UserInfo
        UserInfo userBuddy = userService.findByEmail(buddy.getEmail());

        if (userOwner == null) {
            throw new DataNotFoundException("L'utilisateur n'existe pas : " + owner.getFirstName() + owner.getLastName());
        }
        if (userBuddy == null) {
            throw new DataNotFoundException("L'utilisateur n'existe pas : " + buddy.getFirstName() + buddy.getLastName());
        }

        User tempUserOwner = new User();
        tempUserOwner.setEmail(userOwner.getEmail());
        userDAO.findByEmail(tempUserOwner.getEmail());

        User tempUserBuddy = new User();
        tempUserBuddy.setEmail(userBuddy.getEmail());
        userDAO.findByEmail(tempUserBuddy.getEmail());


        userService.addRelation(tempUserOwner, tempUserBuddy);

    }


    @Override
    public void deleteFriend(User owner, User buddy) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
