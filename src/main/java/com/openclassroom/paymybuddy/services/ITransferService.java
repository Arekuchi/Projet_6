package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.InternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.TransferInfo;
import com.openclassroom.paymybuddy.model.Transfer;


import java.util.List;

public interface ITransferService {

    List<TransferInfo> findAll();
    TransferInfo findById(Integer id);
    List<InternalTransferDTO> findInternalTransferByUser(String emailOwner);
    List<ExternalTransferDTO> findExternalTransferByUser(String emailOwner);
    InternalTransferDTO doInternalTransfer(InternalTransferDTO internalTransferDTO);
    ExternalTransferDTO doExternalTransfer(ExternalTransferDTO externalTransferDTO);




    List<TransferInfo> findByStatus(String status);

    Boolean addTransaction(Transfer transfer);
//    Boolean addInternalTransaction(InternalTransfer transfer);
    Boolean addExternalTransaction(ExternalTransferDTO transferDTO);



}
