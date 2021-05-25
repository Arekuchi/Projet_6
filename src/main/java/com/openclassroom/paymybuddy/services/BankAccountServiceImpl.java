package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IBankAccountDAO;
import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.BankAccountDTO;
import com.openclassroom.paymybuddy.DTO.BankAccountInfo;
import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.web.exception.DataAlreadyExistsException;
import com.openclassroom.paymybuddy.web.exception.DataMissingException;
import com.openclassroom.paymybuddy.web.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BankAccountServiceImpl implements IBankAccountService {

    @Autowired
    IBankAccountDAO bankAccountDAO;

    @Autowired
    IUserDAO userDAO;


    @Override
    public List<BankAccountInfo> findAll() {
        List<BankAccount> bankAccountList = bankAccountDAO.findAll();
        List<BankAccountInfo> bankAccountInfoList = new ArrayList<>();

        for (BankAccount bankAccount : bankAccountList) {
            BankAccountInfo bankAccountInfo = new BankAccountInfo();
            bankAccountInfo.setAccountName(bankAccount.getAccountName());
            bankAccountInfo.setIban(bankAccount.getIban());
            bankAccountInfo.setBankName(bankAccount.getBankName());
            bankAccountInfo.setUserEmail(bankAccount.getUser().getEmail());

            bankAccountInfoList.add(bankAccountInfo);
        }
        return bankAccountInfoList;
    }

    @Override
    public BankAccountInfo findByIban(String iban) {
        BankAccount bankAccount = bankAccountDAO.findBankAccountByIban(iban);
        BankAccountInfo bankAccountInfo = new BankAccountInfo();
        bankAccountInfo.setAccountName(bankAccount.getAccountName());
        bankAccountInfo.setIban(bankAccount.getIban());
        bankAccountInfo.setBankName(bankAccount.getBankName());
        bankAccountInfo.setUserEmail(bankAccount.getUser().getEmail());

        return bankAccountInfo;

    }

    @Override
    public BankAccountInfo findByEmail(String email) {
        BankAccount bankAccount = bankAccountDAO.findByUserEmail(email);
        BankAccountInfo bankAccountInfo = new BankAccountInfo();
        bankAccountInfo.setAccountName(bankAccount.getAccountName());
        bankAccountInfo.setIban(bankAccount.getIban());
        bankAccountInfo.setBankName(bankAccount.getBankName());
        bankAccountInfo.setUserEmail(bankAccount.getUser().getEmail());

        return bankAccountInfo;

    }

    @Override
    public List<BankAccountInfo> findByBankName(String bankName) {
        List<BankAccount> bankAccountList = bankAccountDAO.findByBankName(bankName);
        List<BankAccountInfo> bankAccountInfoList = new ArrayList<>();

        for (BankAccount bankAccount : bankAccountList) {
            BankAccountInfo bankAccountInfo = new BankAccountInfo();
            bankAccountInfo.setAccountName(bankAccount.getAccountName());
            bankAccountInfo.setBankName(bankAccount.getBankName());
            bankAccountInfo.setIban(bankAccount.getIban());
            bankAccountInfo.setUserEmail(bankAccount.getUser().getEmail());

            bankAccountInfoList.add(bankAccountInfo);
        }
        return bankAccountInfoList;
    }


    @Override
    public BankAccount addBankAccount(String emailOwner, BankAccountDTO bankAccountDTO) {


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

            bankAccountDAO.save(bankAccount);

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


    public boolean deleteBankAccountByUserEmail(String email) {

        // on vérifie que le param est bien rempli
        if (email.isEmpty() || email.isBlank()) {
            throw new DataMissingException("L'email de l'utilisateur ne peut être vide");
        }
        // on vérifie que l'email existe dans la DB
        if (!userDAO.existsByEmail(email)) {
            throw new InvalidArgumentException("L'email de l'utilisateur n'existe pas");
        }

        bankAccountDAO.delete(bankAccountDAO.findByUserEmail(email));

        return true;
    }
}
