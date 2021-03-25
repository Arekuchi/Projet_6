package com.openclassroom.paymybuddy.services;


import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl {

    private EntityManager entityManager;


    public void UserDAO(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }




    double balancedefault = 0.00;

    long now = System.currentTimeMillis();
    java.sql.Timestamp dateSQL = new Timestamp(now);


    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(BankAccount.class)
            .buildSessionFactory();


    Session session = factory.getCurrentSession();

    public void CreateUser() {
        try {

            // create a user
            System.out.println("Creating a new User object");
            User tempUser = new User("Nicolas", "Sarkozy", "nonpresident@gmail.com", "password", balancedefault, dateSQL);
            // start transaction
            session.beginTransaction();
            // save the user
            System.out.println("Saving the new User" + tempUser.getFirstName() + tempUser.getLastName());
            session.save(tempUser);
            // commit the transaction
            System.out.println("Done");
            session.getTransaction().commit();

        } finally {

            session.close();
            System.out.println("Closing the Session");
            factory.close();
        }

    }

    public void CreateUser(User user) {
        try {

            // create a user
            System.out.println("Creating a new User object");
            User tempUser = new User("Nicolas", "Sarkozy", "nonpresident@gmail.com", "password", balancedefault, dateSQL);
            // start transaction
            session.beginTransaction();
            // save the user
            System.out.println("Saving the new User" + tempUser.getFirstName() + tempUser.getLastName());
            session.save(tempUser);
            // commit the transaction
            System.out.println("Done");
            session.getTransaction().commit();

        } finally {

            session.close();
            System.out.println("Closing the Session");
            factory.close();
        }

    }

    public void AddBankAccount() {
        try {

            // start transaction
            session.beginTransaction();

            // get the user field from db
            int theId = 9;
            User tempUser = session.get(User.class, theId);

            // create a bank account
            BankAccount tempBankAccount1 = new BankAccount("iban_" + tempUser.getLastName(), "bic_" + tempUser.getLastName(), "Banque Populaire", "Compte courant");

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


    public List<User> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> theQuery = currentSession.createQuery("from User", User.class);

        List<User> users = theQuery.getResultList();

        return users;
    }

}
