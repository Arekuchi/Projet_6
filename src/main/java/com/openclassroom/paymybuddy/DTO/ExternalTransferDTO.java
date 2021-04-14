package com.openclassroom.paymybuddy.DTO;

import com.openclassroom.paymybuddy.model.User;

import java.math.BigDecimal;

public class ExternalTransferDTO {

    // fields
    private User user;
    private BigDecimal amount;
    private String iban;

    // constructors

    public ExternalTransferDTO() {
    }

    public ExternalTransferDTO(User user, BigDecimal amount, String iban) {
        this.user = user;
        this.amount = amount;
        this.iban = iban;
    }

    // getters & setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
