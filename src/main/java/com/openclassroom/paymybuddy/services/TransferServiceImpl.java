package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.ITransferDAO;
import com.openclassroom.paymybuddy.DTO.TransferInfo;
import com.openclassroom.paymybuddy.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransferServiceImpl implements ITransferService {

    @Autowired
    ITransferDAO transferDAO;

    @Override
    public List<TransferInfo> findAll() {
        List<Transfer> transferList = transferDAO.findAll();
        List<TransferInfo> transferInfoList = new ArrayList<>();

        for (Transfer transfer : transferList) {
            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setId(transfer.getId());
            transferInfo.setFees(transfer.getAmount());
            transferInfo.setDescription(transfer.getDescription());
            transferInfo.setTransactionDate(transfer.getTransactionDate());

            transferInfoList.add(transferInfo);
        }

        return transferInfoList;
    }

}
