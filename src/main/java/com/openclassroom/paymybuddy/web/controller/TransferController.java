package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DTO.*;
import com.openclassroom.paymybuddy.model.Transfer;
import com.openclassroom.paymybuddy.services.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {

    @Autowired
    ITransferService transferService;

    // POST - CREATE

    @PostMapping("/Transfer")
    public void getTransfer(@RequestBody Transfer transfer) throws Exception {

    }

    @PostMapping("/Transfer/Internal")
    @ResponseStatus(HttpStatus.CREATED)
    public InternalTransferDTO doInternalTransfer(@RequestBody InternalTransferDTO internalTransferDTO) {
        return transferService.doInternalTransfer(internalTransferDTO);

    }

    @PostMapping("/Transfer/External")
    @ResponseStatus(HttpStatus.CREATED)
    public ExternalTransferDTO doExternalTransfer(@RequestBody ExternalTransferDTO externalTransferDTO) {
         return transferService.doExternalTransfer(externalTransferDTO);
    }


    // GET - READ

    @GetMapping("/Transfers")
    @ResponseStatus(HttpStatus.OK)
    public List<TransferInfo> getTransfers() {
        return transferService.findAll();
    }

    @GetMapping("/Transfer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransferInfo getTransferById(@PathVariable Integer id) {

        return transferService.findById(id);
    }

    @GetMapping("/Transfers/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransferInfo> getTransfersByStatus(@PathVariable String status) {

        return transferService.findByStatus(status);
    }


}
