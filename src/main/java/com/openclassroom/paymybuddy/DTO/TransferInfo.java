package com.openclassroom.paymybuddy.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransferInfo {

    // fields
    private Integer id;
    private BigDecimal fees;
    private String description;
    private Timestamp transactionDate;

    // constructors

    public TransferInfo() {
    }

    public TransferInfo(Integer id, BigDecimal fees, String description, Timestamp transactionDate) {
        this.id = id;
        this.fees = fees;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    // getters & setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
