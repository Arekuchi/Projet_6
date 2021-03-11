package com.openclassroom.paymybuddy;

import com.openclassroom.paymybuddy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;

public class CreateUser {
    public static void main(String[] args) {

        double balancedefault = 0.00;

        long now = System.currentTimeMillis();
        java.sql.Timestamp dateSQL = new Timestamp(now);


                SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();


        try {

            // create a user
            System.out.println("Creating a new User object");
            User tempUser = new User("André", "Berthelot", "2mail@gmail.com", "password", balancedefault, dateSQL);
            // start transaction
            session.beginTransaction();
            // save the user
            System.out.println("Saving the new User" + tempUser.getFirstName() + tempUser.getLastName());
            session.save(tempUser);
            // commit the transaction
            System.out.println("Done");
            session.getTransaction().commit();

        } finally {
            System.out.println("Closing the Session");
            factory.close();
        }
    }
}
