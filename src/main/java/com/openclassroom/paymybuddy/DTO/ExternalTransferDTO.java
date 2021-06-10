package com.openclassroom.paymybuddy.DTO;

import com.openclassroom.paymybuddy.model.User;

import java.math.BigDecimal;

public class ExternalTransferDTO {

    // fields
    private Integer id;
    private String ibanUser;
    private BigDecimal amount;
    private String emailUser;
    private String description;
    private BigDecimal fees;

    // getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIbanUser() {
        return ibanUser;
    }

    public void setIbanUser(String ibanUser) {
        this.ibanUser = ibanUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    // constructors

    public ExternalTransferDTO() {
    }

    public ExternalTransferDTO(Integer id, String ibanUser, BigDecimal amount, String emailUser) {
        this.id = id;
        this.ibanUser = ibanUser;
        this.amount = amount;
        this.emailUser = emailUser;
    }
}
