package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.IInternalTransferDAO;
import com.openclassroom.paymybuddy.DAO.ITransferDAO;
import com.openclassroom.paymybuddy.DAO.IUserDAO;
import com.openclassroom.paymybuddy.DTO.InternalTransferInfo;
import com.openclassroom.paymybuddy.DTO.TransferInfo;
import com.openclassroom.paymybuddy.model.ExternalTransfer;
import com.openclassroom.paymybuddy.model.InternalTransfer;
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

    @Autowired
    IInternalTransferDAO internalTransferDAO;

    @Autowired
    IUserDAO userDAO;

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

    public TransferInfo findById(Integer id) {
        Transfer transfer = transferDAO.getOne(id);
        TransferInfo transferInfo = new TransferInfo();
        transferInfo.setTransactionDate(transfer.getTransactionDate());
        transferInfo.setDescription(transfer.getDescription());
        transferInfo.setFees(transfer.getAmount());
        transferInfo.setId(transfer.getId());

        return transferInfo;
    }

    public List<TransferInfo> findByStatus(String status) {
        List<Transfer> transferList = transferDAO.findByStatus(status);
        List<TransferInfo> transferInfoList = new ArrayList<>();

        for (Transfer transfer : transferList) {
            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setId(transfer.getId());
            transferInfo.setDescription(transfer.getDescription());
            transferInfo.setFees(transfer.getAmount());
            transferInfo.setTransactionDate(transfer.getTransactionDate());

            transferInfoList.add(transferInfo);

        }

        return transferInfoList;
    }

    // Méthode addInternalTransaction

    public Boolean addInternalTransaction(InternalTransferInfo internalTransferInfo) {

        InternalTransfer internalTransfer = new InternalTransfer();
        internalTransfer.setReceiverID(userDAO.getOne(internalTransferInfo.getSenderId()));
        internalTransfer.setReceiverID(userDAO.getOne(internalTransferInfo.getReceiverId()));

        transferDAO.save(internalTransfer);
        return true;
    }
    // Méthode addExternalTransaction

    public Boolean addExternalTransaction(ExternalTransfer externalTransfer) {

        ExternalTransfer tempExternalTransfer = new ExternalTransfer();
        tempExternalTransfer.setAmount(externalTransfer.getAmount());
        tempExternalTransfer.setTransactionDate(externalTransfer.getTransactionDate());
        tempExternalTransfer.setDescription(externalTransfer.getDescription());
        tempExternalTransfer.setFees(externalTransfer.getFees());


        transferDAO.save(tempExternalTransfer);
        return true;
    }
}
