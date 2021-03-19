package com.openclassroom.paymybuddy;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.ExternalTransfer;
import com.openclassroom.paymybuddy.model.Transfer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;

public class CreateExternalTransfer {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ExternalTransfer.class)
                .buildSessionFactory();


        long now = System.currentTimeMillis();
        Timestamp dateSQL = new Timestamp(now);


        Session session = factory.getCurrentSession();



        try {

            // create a Transfer
            Transfer tempTransfer = new Transfer(3, 500.00, "virement initial", dateSQL, "IN PROGRESS");

            BankAccount tempBankAccount1 = new BankAccount("iban_Sarkozy" , "bic_Sarkozy", "Banque Populaire", "Compte courant");

            int theId = 5;
            Transfer tempTransfer2 = session.get(Transfer.class, theId);
            String iban = "iban_Sarkozy";
            BankAccount tempBankAccount = session.get(BankAccount.class, iban);
//            ExternalTransfer tempExternalTransfer2 = new ExternalTransfer(3, tempBankAccount.getIban(), tempTransfer2.getAmount() / 10);
//
//            System.out.println("Creating a new Transfer object");
//            ExternalTransfer tempExternalTransfer = new ExternalTransfer(2, "iban_Berthelot", 50.00);





            // start transaction

            session.beginTransaction();

            // save the Transfer
//            session.save(tempExternalTransfer);
//            session.save(tempExternalTransfer2);
//

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
