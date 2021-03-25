package com.openclassroom.paymybuddy.model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "transfer_id") // On va chercher la PK de l'entité mère
@Table(name="internal_transfer")
public class InternalTransfer extends Transfer {


    // fields

    @Id
    @JoinColumn(name="transfer_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id_sender")
    private User senderID;
    @ManyToOne
    @JoinColumn(name="user_id_receiver")
    private User receiverID;


    // constructors

    public InternalTransfer() {
    }

    public InternalTransfer(User senderID, User receiverID) {
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    public InternalTransfer(int id, User senderID, User receiverID) {
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    // getters & setters


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSenderID() {
        return senderID;
    }

    public void setSenderID(User senderID) {
        this.senderID = senderID;
    }

    public User getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(User receiverID) {
        this.receiverID = receiverID;
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
