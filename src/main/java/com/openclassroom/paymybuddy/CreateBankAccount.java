package com.openclassroom.paymybuddy;

import com.openclassroom.paymybuddy.model.BankAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateBankAccount {
    public static void main(String[] args) {


        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(BankAccount.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();

        try {

            // create a bankaccount
            System.out.println("Creating a new BankAccount object");
            BankAccount tempBankAccount = new BankAccount("iban_Berthelot", "bic_Berthelot", "Banque Postale", "Compte courrant", 3);
            // start transaction
            session.beginTransaction();
            // save the user
            System.out.println("Saving the new account" + tempBankAccount.getIban() + tempBankAccount.getAccountName());
            session.save(tempBankAccount);
            // commit the transaction
            System.out.println("Done");
            session.getTransaction().commit();



        } finally {
            System.out.println("Closing the Session");
            factory.close();

        }
    }
}
