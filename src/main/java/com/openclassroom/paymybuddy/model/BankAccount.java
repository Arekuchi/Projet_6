package com.openclassroom.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table(name="bank_account")
public class BankAccount {


    // fields

    @Id
    @Column(name="iban", length = 34)
    private String iban;

    @Column(name="bic")
    private String bic;
    @Column(name="bank_name")
    private String bankName;
    @Column(name="account_name")
    private String accountName;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;


    // constructors

    public BankAccount() {

    }

    public BankAccount(String bic, String bankName, String accountName) {
        this.bic = bic;
        this.bankName = bankName;
        this.accountName = accountName;
    }

    public BankAccount(String iban, String bic, String bankName, String accountName) {
        this.iban = iban;
        this.bic = bic;
        this.bankName = bankName;
        this.accountName = accountName;
    }


    // getters & setters

    public String getIban() {

        return iban;
    }

    public void setIban(String iban) {

        this.iban = iban;
    }

    public String getBic() {

        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return user.getId();
    }

    public String userEmail() {return user.getEmail();}

    // convinience
    @Override
    public String toString() {
        return "BankAccount{" +
                "iban='" + iban + '\'' +
                ", bic='" + bic + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
