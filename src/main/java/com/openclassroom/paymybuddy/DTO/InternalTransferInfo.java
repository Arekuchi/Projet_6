package com.openclassroom.paymybuddy.DTO;

import javax.persistence.criteria.CriteriaBuilder;

public class InternalTransferInfo {

    // fields
    private Integer senderId;
    private Integer receiverId;

    // Constructors

    public InternalTransferInfo() {
    }

    public InternalTransferInfo(Integer senderId, Integer receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    // getters & setters

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

}
