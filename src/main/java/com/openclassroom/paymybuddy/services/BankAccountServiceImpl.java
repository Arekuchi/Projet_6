package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IBankAccountDAO;
import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.BankAccountInfo;
import com.openclassroom.paymybuddy.DTO.UserInfo;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
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
        BankAccount bankAccount = bankAccountDAO.findByIban(iban);
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
}
