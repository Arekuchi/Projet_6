package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.*;
import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.InternalTransferDTO;
import com.openclassroom.paymybuddy.model.*;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import com.openclassroom.paymybuddy.web.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    IBankAccountDAO bankAccountDAO;

    @Autowired
    IExternalTransferDAO externalTransferDAO;


    @Override
    public List<InternalTransferDTO> findInternalTransferByUser(String emailOwner) {

        List<InternalTransferDTO> internalTransferDTOList = new ArrayList<>();

        for (InternalTransfer internalTransfer : internalTransferDAO.findAllBySenderID_EmailOrderByTransactionDateDesc(emailOwner)) {

            InternalTransferDTO internalTransferDTO = new InternalTransferDTO();
            internalTransferDTO.setEmailSender(internalTransfer.getSenderID().getEmail());
            internalTransferDTO.setEmailReceiver(internalTransfer.getReceiverID().getEmail());
            internalTransferDTO.setAmount(internalTransfer.getAmount());
            internalTransferDTO.setId(internalTransfer.getId());
            internalTransferDTO.setDescription(internalTransfer.getDescription());

            internalTransferDTOList.add(internalTransferDTO);

        }

        return internalTransferDTOList;
    }

    @Override
    public List<ExternalTransferDTO> findExternalTransferByUser(String emailOwner) {

        List<ExternalTransferDTO> externalTransferDTOList = new ArrayList<>();
        for (ExternalTransfer externalTransfer : externalTransferDAO.findAllByBankAccount_User_EmailOrderByTransactionDateDesc(emailOwner)) {
            ExternalTransferDTO dto = new ExternalTransferDTO();
            dto.setIbanUser(externalTransfer.getBankAccount().getIban());
            dto.setDescription(externalTransfer.getDescription());
            dto.setAmount(externalTransfer.getAmount());
            dto.setFees(externalTransfer.getFees());
            externalTransferDTOList.add(dto);
        }
        return externalTransferDTOList;

    }


    // Méthode addTransfer



    // Méthode addInternalTransaction



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


        // récupérer le bank account en fontion de l'iban et de l'email du user
        BankAccount bankAccount = bankAccountDAO.findBankAccountByIbanAndUser_Email(externalTransferDTO.getIbanUser(), externalTransferDTO.getEmailUser());
        User user = bankAccount.getUser();
        // On attribut le dernier iban ajouté.
        // Fees
        BigDecimal fee = externalTransferDTO.getAmount().multiply(BigDecimal.valueOf(0.005));

        ExternalTransfer externalTransfer = new ExternalTransfer();
        externalTransfer.setAmount(externalTransferDTO.getAmount());
        externalTransfer.setDescription(externalTransferDTO.getDescription());
        externalTransfer.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        externalTransfer.setStatus("COMPLETED");
        externalTransfer.setFees(fee);
        externalTransfer.setBankAccount(bankAccount);

        transferDAO.save(externalTransfer);

        externalTransferDTO.setId(externalTransfer.getId());
        user.setBalance(user.getBalance().add(externalTransfer.getAmount().subtract(fee)));

        userDAO.save(user);

        return externalTransferDTO;


    }



}
