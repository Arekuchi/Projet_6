package com.openclassroom.paymybuddy.model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "transfer_id") // On va chercher la PK de l'entité mère
@Table(name="external_transfer")
public class ExternalTransfer extends Transfer {


    // fields

    @Id
    @Column(name="transfer_id")
    private int id;


    @Column(name="fees")
    private double fees;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="bank_account_iban")
    private BankAccount bankAccount ;


    // constructors

    public ExternalTransfer() {
    }

    public ExternalTransfer(double fees) {

        this.fees = fees;
    }

    public ExternalTransfer(int id, double fees) {
        this.id = id;

        this.fees = fees;
    }

    // getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }


    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public String getBankAccountIban() {
        return bankAccount.getIban();
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "ExternalTransfer{" +
                "id=" + id +
                ", fees=" + fees +
                '}';
    }
}
