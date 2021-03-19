package com.openclassroom.paymybuddy;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.InternalTransfer;
import com.openclassroom.paymybuddy.model.Transfer;
import com.openclassroom.paymybuddy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInternalTransfer {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(InternalTransfer.class)
                .addAnnotatedClass(Transfer.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();


        try {


            // get the User from DB
            int theId = 2;
            int theId2 = 3;
            int theId3 = 3;
            User tempUser = session.get(User.class, theId);
            User tempUser2 = session.get(User.class, theId2);
            Transfer tempTransfer = session.get(Transfer.class, theId3);

            // create a InternalTransfer
            System.out.println("Creating a new Internal Transfer object");
//            InternalTransfer tempInternalTransfer = new InternalTransfer(3, tempUser.getId(), tempUser2.getId(), tempTransfer, tempUser.getId(), tempUser2.getId());

            // start transaction
            session.beginTransaction();
            // save the user

//            session.save(tempInternalTransfer);
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
