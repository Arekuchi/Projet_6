package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.InternalTransferDTO;

import java.util.List;

public interface ITransferService {


    List<InternalTransferDTO> findInternalTransferByUser(String emailOwner);
    List<ExternalTransferDTO> findExternalTransferByUser(String emailOwner);
    InternalTransferDTO doInternalTransfer(InternalTransferDTO internalTransferDTO);
    ExternalTransferDTO doExternalTransfer(ExternalTransferDTO externalTransferDTO);






}
