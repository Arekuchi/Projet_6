package com.openclassroom.paymybuddy;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateBankAccount {
    public static void main(String[] args) {



        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(BankAccount.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();

        try {

            // start transaction
            session.beginTransaction();

            // get the user field from db
            int theId = 9;
            User tempUser = session.get(User.class, theId);

            // create a bank account
            BankAccount tempBankAccount1 = new BankAccount("iban_" + tempUser.getLastName(), "bic_" + tempUser.getLastName(), "Banque Populaire", "Compte courant");
            BankAccount tempBankAccount2 = new BankAccount();

            // add bank account to user
            tempUser.add(tempBankAccount1);

            // save the bankaccount
            System.out.println("Saving the new account" + tempBankAccount1.getIban() + tempBankAccount1.getAccountName());
            session.save(tempBankAccount1);
            // commit the transaction
            System.out.println("Done");
            session.getTransaction().commit();



        } finally {
            System.out.println("Closing the Session");
            factory.close();

        }
    }
}
