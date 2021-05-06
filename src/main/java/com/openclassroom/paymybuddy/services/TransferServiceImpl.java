package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IInternalTransferDAO;
import com.openclassroom.paymybuddy.DAO.IRelationDAO;
import com.openclassroom.paymybuddy.DAO.ITransferDAO;
import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.InternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.TransferInfo;
import com.openclassroom.paymybuddy.model.*;
import com.openclassroom.paymybuddy.web.exception.DataMissingException;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import com.openclassroom.paymybuddy.web.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class TransferServiceImpl implements ITransferService {


    @Autowired
    ITransferDAO transferDAO;

    @Autowired
    IInternalTransferDAO internalTransferDAO;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IRelationDAO relationDAO;

    @Override
    public List<TransferInfo> findAll() {
        List<Transfer> transferList = transferDAO.findAll();
        List<TransferInfo> transferInfoList = new ArrayList<>();

        for (Transfer transfer : transferList) {
            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setId(transfer.getId());
            transferInfo.setFees(transfer.getAmount());
            transferInfo.setDescription(transfer.getDescription());
            transferInfo.setTransactionDate(transfer.getTransactionDate());

            transferInfoList.add(transferInfo);
        }

        return transferInfoList;
    }

    public TransferInfo findById(Integer id) {
        Transfer transfer = transferDAO.getOne(id);
        TransferInfo transferInfo = new TransferInfo();
        transferInfo.setTransactionDate(transfer.getTransactionDate());
        transferInfo.setDescription(transfer.getDescription());
        transferInfo.setFees(transfer.getAmount());
        transferInfo.setId(transfer.getId());

        return transferInfo;
    }


    @Override
    public List<TransferInfo> findByStatus(String status) {
        List<Transfer> transferList = transferDAO.findByStatus(status);
        List<TransferInfo> transferInfoList = new ArrayList<>();

        for (Transfer transfer : transferList) {
            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setId(transfer.getId());
            transferInfo.setDescription(transfer.getDescription());
            transferInfo.setFees(transfer.getAmount());
            transferInfo.setTransactionDate(transfer.getTransactionDate());

            transferInfoList.add(transferInfo);

        }

        return transferInfoList;
    }

    // Méthode addTransfer

    @Override
    public Boolean addTransaction(Transfer transfer) {


        transferDAO.save(transfer);
        return true;
    }

    // Méthode addInternalTransaction

    public Boolean addInternalTransaction(InternalTransfer internalTransfer) {

        InternalTransfer tempInternalTransfer = new InternalTransfer();
        tempInternalTransfer.setReceiverID(internalTransfer.getReceiverID());
        tempInternalTransfer.setReceiverID(internalTransfer.getSenderID());

        transferDAO.save(tempInternalTransfer);
        return true;
    }

    @Override
    public Boolean addExternalTransaction(ExternalTransferDTO transferDTO) {
        return null;
    }



    @Override
    public InternalTransferDTO doInternalTransfer(InternalTransferDTO internalTransferDTO) {

        // vérif que les deux users sont amis
        Relation relation = relationDAO.findByOwner_EmailAndBuddy_Email(internalTransferDTO.getEmailSender(), internalTransferDTO.getEmailReceiver());

        if(relation == null) {
            throw new DataNotFoundException("Les utilisateurs ne sont pas amis");
        }
        // vérif que le sender a assez pour le transfer
        if(internalTransferDTO.getAmount().compareTo(relation.getOwner().getBalance()) > 0) {
           throw new InvalidArgumentException("L'utilisataur n'a pas assez pour l'envoi");
        }

        InternalTransfer internalTransfer = new InternalTransfer();
        internalTransfer.setSenderID(relation.getOwner());
        internalTransfer.setReceiverID(relation.getBuddy());
        internalTransfer.setStatus("COMPLETED");
        internalTransfer.setAmount(internalTransferDTO.getAmount());
        internalTransfer.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        internalTransfer.setDescription(internalTransferDTO.getDescription());

        transferDAO.save(internalTransfer);

        internalTransferDTO.setId(internalTransfer.getId());

        // on mets à jour les comptes des users
        relation.getOwner().setBalance(relation.getOwner().getBalance().subtract(internalTransferDTO.getAmount()));
        relation.getBuddy().setBalance(relation.getBuddy().getBalance().add(internalTransferDTO.getAmount()));

        userDAO.save(relation.getOwner());
        userDAO.save(relation.getBuddy());

        return internalTransferDTO;
    }

    @Override
    public ExternalTransferDTO doExternalTransfer(ExternalTransferDTO externalTransferDTO) {

        //vérif que le User existe (findByEmail) puis if
        User user = userDAO.findByEmail(externalTransferDTO.getEmailUser());
        if (user == null) {
            throw new DataMissingException("L'utilisateur n'existe pas");
        }

        // on check que l'iban appartient à l'utilisateur (bankaccountlist)

//        List<BankAccount> bankAccountList = user.getBankAccountList();
//        for (BankAccount bankAccount : bankAccountList) {
//            if (!Arrays.asList(bankAccountList).contains(externalTransferDTO.getIbanUser())) {
//                throw new InvalidArgumentException("Le compte n'appartient pas à cet utilisateur");
//            }
//        }

        boolean ibanUserIsTrue = user.getBankAccountList().toString().contains(externalTransferDTO.getIbanUser());
        System.out.println(ibanUserIsTrue);
        if (!ibanUserIsTrue) {
            throw new InvalidArgumentException("Le compte n'appartient pas à cet utilisateur");
        }

        // sommes de transfer positive uniquement
        if (externalTransferDTO.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidArgumentException("La somme du la transaction est négative");
        }


        // faire le métier, crée un externaltransfer, puis le map externaltransferDTO
        ExternalTransfer externalTransfer = new ExternalTransfer();
        externalTransfer.setAmount(externalTransferDTO.getAmount());
        externalTransfer.setFees(externalTransferDTO.getAmount().doubleValue() / 10);
        externalTransfer.setDescription(externalTransferDTO.getDescription());
        externalTransfer.setStatus("COMPLETED");
        externalTransfer.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));

        //on doit remplir la colonne bank_account_iban
        externalTransfer.setBankAccount(user.getBankAccountList().get(0)); // on va chercher l'iban numéro 1 du User (donc son premier BankAccount)




        // save externaltransfer
        transferDAO.save(externalTransfer);

        externalTransferDTO.setId(externalTransfer.getId());

        // update User (balance)

        user.setBalance(user.getBalance().add(externalTransferDTO.getAmount()));

        //changer le void en externaltransferDTO et rajouter l'id (return)

        return externalTransferDTO;


    }



}
