package com.openclassroom.paymybuddy.DTO;

public class BankAccountInfo {

    //fields

    private String iban;
    private String bankName;
    private String accountName;
    private String userEmail;

    // constructors

    public BankAccountInfo() {
    }

    public BankAccountInfo(String bankName, String accountName, String userEmail) {
        this.bankName = bankName;
        this.accountName = accountName;
        this.userEmail = userEmail;
    }

    public BankAccountInfo(String iban, String bankName, String accountName, String userEmail) {
        this.iban = iban;
        this.bankName = bankName;
        this.accountName = accountName;
        this.userEmail = userEmail;
    }

    // getters & setters


    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
