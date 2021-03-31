package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DTO.TransferInfo;
import com.openclassroom.paymybuddy.model.Transfer;

import java.util.List;

public interface ITransferService {

    List<TransferInfo> findAll();
}
