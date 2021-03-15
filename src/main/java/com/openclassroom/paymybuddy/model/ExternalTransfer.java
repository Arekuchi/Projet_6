package com.openclassroom.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table(name="external_transfer")
public class ExternalTransfer {


    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transfer_id")
    private int id;

    @Column(name="bank_account_iban", length = 34)
    private String iban;
    @Column(name="fees")
    private double fees;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="transfer_id")
    private Transfer transfer;

    @ManyToOne
    @JoinColumn(name="bank_account_iban")
    private BankAccount bankAccount ;


    // constructors

    public ExternalTransfer() {
    }

    public ExternalTransfer(int id, String iban, double fees) {
        this.id = id;
        this.iban = iban;
        this.fees = fees;
    }

    public ExternalTransfer(int id, String iban, double fees, Transfer transfer, BankAccount bankAccount) {
        this.id = id;
        this.iban = iban;
        this.fees = fees;
        this.transfer = transfer;
        this.bankAccount = bankAccount;
    }

    // getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "ExternalTransfer{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", fees=" + fees +
                '}';
    }
}
