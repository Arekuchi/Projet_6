package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.InternalTransferInfo;
import com.openclassroom.paymybuddy.DTO.TransferInfo;
import com.openclassroom.paymybuddy.model.InternalTransfer;
import com.openclassroom.paymybuddy.model.Transfer;


import java.util.List;

public interface ITransferService {

    List<TransferInfo> findAll();
    TransferInfo findById(Integer id);
    Boolean addTransaction(Transfer transfer);
//    Boolean addInternalTransaction(InternalTransfer transfer);
    Boolean addExternalTransaction(ExternalTransferDTO transferDTO);
}
