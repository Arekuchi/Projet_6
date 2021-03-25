package com.openclassroom.paymybuddy;


import com.openclassroom.paymybuddy.DAO.*;
import com.openclassroom.paymybuddy.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;


@SpringBootApplication
public class PaymybuddyApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(PaymybuddyApplication.class, args);

		// test de la connection JPA
		IUserDAO userDAO = context.getBean(IUserDAO.class);
		System.out.println("Liste des Users : " + userDAO.findAll());

		// test liaison User + BankAccount
		List<User> userList = userDAO.findAll();
		System.out.println("Iban numéro 1 " + userList.get(0).getBankAccountList().get(0).getIban());

		// test BankAccount
		IBankAccountDAO bankAccountDAO = context.getBean(IBankAccountDAO.class);
		System.out.println("Liste des comptes bancaires : " + bankAccountDAO.findAll());

		// test ExternalTransfer
		IExternalTransferDAO externalTransferDAO = context.getBean(IExternalTransferDAO.class);
		System.out.println("Description du premier ExternalTransfer = " + externalTransferDAO.findAll().get(0).getDescription());

		// test InternalTransfer
		IInternalTransferDAO internalTransferDAO = context.getBean(IInternalTransferDAO.class);
		System.out.println("Description du premier InternalTransfer = " + internalTransferDAO.findAll().get(0).getDescription());

//		// test Relation
//		System.out.println("Relation des utilsateurs = " + userDAO.findAll().get(0).getRelationList().get(0));
	}
}
