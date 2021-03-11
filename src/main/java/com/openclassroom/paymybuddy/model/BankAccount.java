package com.openclassroom.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.openclassroom.paymybuddy.model.User;

@Entity
@Table(name="bank_account")
public class BankAccount {

    @Id
    @Column(name="iban")
    private String iban;

    @Column(name="bic")
    private String bic;
    @Column(name="bank_name")
    private String bankName;
    @Column(name="account_name")
    private String accountName;
    @Column(name="user_id")
    private int userId;

    public BankAccount() {

    }

    public BankAccount(String iban, String bic, String bankName, String accountName, int userId) {
        this.iban = iban;
        this.bic = bic;
        this.bankName = bankName;
        this.accountName = accountName;
        this.userId = userId;
    }

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

    public int getUserId() {
        User tempUser = new User();
        int userId = tempUser.getId();

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
