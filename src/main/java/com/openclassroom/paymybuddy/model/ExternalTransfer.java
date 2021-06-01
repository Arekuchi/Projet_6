package com.openclassroom.paymybuddy.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@PrimaryKeyJoinColumn(name = "transfer_id") // On va chercher la PK de l'entité mère
@Table(name="external_transfer")
public class ExternalTransfer extends Transfer {


    // fields

    @Column(name="fees")
    private BigDecimal fees;


    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="bank_account_iban")
    private BankAccount bankAccount ;


    // constructors

    public ExternalTransfer() {
    }

    public ExternalTransfer(BigDecimal amount, String description, Timestamp transactionDate, String status) {
        super(amount, description, transactionDate, status);
    }

// getters & setters


    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
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
                ", fees=" + fees +
                '}';
    }
}
