package com.openclassroom.paymybuddy.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="transfer")
public class Transfer {


    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="amount")
    private double amount;
    @Column(name="description")
    private String description;
    @Column(name="transaction_date")
    private Timestamp transactionDate;
    @Column(name="status")
    private String status;


    @OneToMany(mappedBy="transfer", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ExternalTransfer> externalTransferList;

    @OneToMany(mappedBy="transfer", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<InternalTransfer> internalTransferList;


    // constructors

    public Transfer() {
    }

    public Transfer(int id, double amount, String description, Timestamp transactionDate, String status) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.status = status;
    }

    public Transfer(int id, double amount, String description, Timestamp transactionDate, String status, List<ExternalTransfer> externalTransferList, List<InternalTransfer> internalTransferList) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.status = status;
        this.externalTransferList = externalTransferList;
        this.internalTransferList = internalTransferList;
    }

    // getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ExternalTransfer> getExternalTransferList() {
        return externalTransferList;
    }

    public void setExternalTransferList(List<ExternalTransfer> externalTransferList) {
        this.externalTransferList = externalTransferList;
    }

    public List<InternalTransfer> getInternalTransferList() {
        return internalTransferList;
    }

    public void setInternalTransferList(List<InternalTransfer> internalTransferList) {
        this.internalTransferList = internalTransferList;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
