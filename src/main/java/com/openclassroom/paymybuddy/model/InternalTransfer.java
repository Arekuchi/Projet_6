package com.openclassroom.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table(name="internal_transfer")
public class InternalTransfer {


    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name="transfer_id")
    private int id;

    @Column(name="user_id_sender")
    private int senderID;
    @Column(name="user_id_receiver")
    private int receiverID;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="transfer_id")
    private Transfer transfer;

    @ManyToOne
    @JoinColumn(name="user_id")
    private int senderId;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    private int receiverId;


    // constructors

    public InternalTransfer() {
    }

    public InternalTransfer(int id, int senderID, int receiverID) {
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    public InternalTransfer(int id, int senderID, int receiverID, Transfer transfer, int senderId, int receiverId) {
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.transfer = transfer;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    // getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "InternalTransfer{" +
                "id=" + id +
                ", senderID=" + senderID +
                ", receiverID=" + receiverID +
                '}';
    }
}
