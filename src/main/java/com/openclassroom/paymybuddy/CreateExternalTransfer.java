package com.openclassroom.paymybuddy;

import com.openclassroom.paymybuddy.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;

public class CreateExternalTransfer {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ExternalTransfer.class)
                .addAnnotatedClass(BankAccount.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Relation.class)
                .addAnnotatedClass(Transfer.class)
                .buildSessionFactory();

        long now = System.currentTimeMillis();
        Timestamp dateSQL = new Timestamp(now);

        Session session = factory.getCurrentSession();

        try {


            // start transaction
            session.beginTransaction();

            //
            BankAccount tempBankAccount = new BankAccount("iban_Sarkozy", "bic_Sarkozy", "Banque Postale", "compte épargne");
            Transfer tempTransfer = new Transfer(500.00, "virement initial", dateSQL, "IN PROGRESS");
////            ExternalTransfer tempExternalTransfer = new ExternalTransfer(tempTransfer.getAmount() / 10);
//            tempExternalTransfer.setStatus("IN PROGRESS");
//            tempExternalTransfer.setTransactionDate(dateSQL);
////            tempExternalTransfer.setBankAccount(tempBankAccount);
//
//
//            // save the Transfer
//            session.save(tempExternalTransfer);


            // commit the transaction
            System.out.println("Done");
            session.getTransaction().commit();

        } finally {

            session.close();
            System.out.println("Closing the Session");
            factory.close();
        }
    }


}
