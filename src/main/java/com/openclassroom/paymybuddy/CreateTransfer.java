package com.openclassroom.paymybuddy;

import com.openclassroom.paymybuddy.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CreateTransfer {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Transfer.class)
                .buildSessionFactory();


        long now = System.currentTimeMillis();
        java.sql.Timestamp dateSQL = new Timestamp(now);


        Session session = factory.getCurrentSession();



        try {

            // create a Transfer

            System.out.println("Creating a new Transfer object");
            Transfer tempTransfer = new Transfer(5, new BigDecimal("500.00"), "virement initial", dateSQL, "IN PROGRESS");




            // start transaction

            session.beginTransaction();

            // save the Transfer
            session.save(tempTransfer);


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
