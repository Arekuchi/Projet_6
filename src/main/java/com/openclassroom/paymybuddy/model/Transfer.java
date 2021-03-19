package com.openclassroom.paymybuddy.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // La PK est partagée dans deux sous-entitées (Internal et External)
@Table(name="transfer")
public class Transfer {


    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="amount")
    private double amount;
    @Column(name="description")
    private String description;
    @Column(name="transaction_date")
    private Timestamp transactionDate;
    @Column(name="status")
    private String status;



    // constructors

    public Transfer() {
    }

    public Transfer(double amount, String description, Timestamp transactionDate, String status) {

        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.status = status;

    }

    public Transfer(int id, double amount, String description, Timestamp transactionDate, String status) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.status = status;

    }

    // getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }

}
