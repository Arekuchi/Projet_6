package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DTO.TransferInfo;


import java.util.List;

public interface ITransferService {

    List<TransferInfo> findAll();
    TransferInfo findById(Integer id);
}
