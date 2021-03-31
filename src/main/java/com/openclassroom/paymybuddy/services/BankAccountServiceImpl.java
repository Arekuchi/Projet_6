package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IBankAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankAccountServiceImpl implements IBankAccountService {

    @Autowired
    IBankAccountDAO bankAccountDAO;
}
