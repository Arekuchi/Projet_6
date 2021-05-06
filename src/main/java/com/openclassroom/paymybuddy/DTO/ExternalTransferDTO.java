package com.openclassroom.paymybuddy.DTO;

import com.openclassroom.paymybuddy.model.User;

import java.math.BigDecimal;

public class ExternalTransferDTO {

    // fields
    private Integer id;
    private String emailUser;
    private BigDecimal amount;
    private String description;
    private String ibanUser;
    private String bankAccountIban;


    // getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIbanUser() {
        return ibanUser;
    }

    public void setIbanUser(String ibanUser) {
        this.ibanUser = ibanUser;
    }


}
