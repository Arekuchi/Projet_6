package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IBankAccountDAO;
import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.BankAccountDTO;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.web.exception.DataAlreadyExistsException;
import com.openclassroom.paymybuddy.web.exception.DataMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class BankAccountServiceImpl implements IBankAccountService {

    @Autowired
    IBankAccountDAO bankAccountDAO;

    @Autowired
    IUserDAO userDAO;


    @Override
    public BankAccount addBankAccount(String emailOwner, BankAccountDTO bankAccountDTO) throws SQLException {


        if (bankAccountDTO.getIban().isBlank()) {
            throw new DataMissingException("L'iban ne peut pas être vide");
        }

        User user = userDAO.findByEmail(emailOwner);
        String iban = bankAccountDTO.getIban();

        BankAccount bankAccount = bankAccountDAO.findBankAccountByIban(iban);

        if (bankAccount == null) {

            bankAccount = new BankAccount();
            bankAccount.setUser(user);
            bankAccount.setIban(bankAccountDTO.getIban());
            bankAccount.setBic(bankAccountDTO.getBic());
            bankAccount.setAccountName(bankAccountDTO.getAccountName());
            bankAccount.setBankName(bankAccountDTO.getBankName());


            try {
                bankAccountDAO.save(bankAccount);
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new SQLException("inside addBankAccount bankAccountDAO.save error");

            }
            return bankAccount;

        } else if (bankAccount.getUser().equals(user)) {
            throw new DataAlreadyExistsException("Vous possédez déjà un compte pour cet IBAN");
        } else {
            throw new DataAlreadyExistsException("Cet IBAN est déjà utilisé");
        }



    }

    @Override
    public Boolean deleteBankAccountByIban(String iban) {

        if (bankAccountDAO.existsById(iban)) {
            bankAccountDAO.deleteById(iban);
            return true;
        }

        return false;
    }

    @Override
    public List<BankAccount> findBankAccountByUser(String username) {

        return bankAccountDAO.findBankAccountsByUser_Email(username);
    }


    @Override
    public Boolean deleteBankAccount(String iban) {
        if (bankAccountDAO.existsById(iban)) {
            bankAccountDAO.deleteById(iban);
            return true;
        }
        return false;
    }

}
