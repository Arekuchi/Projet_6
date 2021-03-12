package com.openclassroom.paymybuddy.model;

import javax.persistence.*;

import com.openclassroom.paymybuddy.model.User;

@Entity
@Table(name="bank_account")
public class BankAccount {


    // fields

    @Id
    @Column(name="iban")
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

    public BankAccount(String iban, String bic, String bankName, String accountName) {
        this.iban = iban;
        this.bic = bic;
        this.bankName = bankName;
        this.accountName = accountName;
    }


    // getters & setters

    public String getIban() {
        User tempUser = new User();
        String iban = "iban_" + tempUser.getLastName();

        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        User tempUser = new User();
        String bic = "bic_" + tempUser.getLastName();

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
