package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DTO.BankAccountDTO;
import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.services.IBankAccountService;
import com.openclassroom.paymybuddy.services.ITransferService;
import com.openclassroom.paymybuddy.services.IUserService;
import com.openclassroom.paymybuddy.web.exception.DataAlreadyExistsException;
import com.openclassroom.paymybuddy.web.exception.DataMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ExternalTransferController {

    @Autowired
    ITransferService transferService;

    @Autowired
    IUserService userService;

    @Autowired
    IBankAccountService bankAccountService;

    @GetMapping("/extransfer")
    public String externalTransferPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        ExternalTransferDTO externalTransferDTO = new ExternalTransferDTO();
        model.addAttribute("externalTransfer", externalTransferDTO);
        model.addAttribute("bankAccount", new BankAccountDTO());
        model.addAttribute("externalTransfers", transferService.findExternalTransferByUser(userDetails.getUsername()));

        List<BankAccount> accounts = bankAccountService.findBankAccountByUser(userDetails.getUsername());
        model.addAttribute("listBankAccount", accounts);

        if (!accounts.isEmpty()) {
            externalTransferDTO.setIbanUser(accounts.get(accounts.size()-1).getIban());
        }


        return "extransfer";
    }

    @PostMapping("/extransfer/addBankAccount")
    public String addBankAccount(@ModelAttribute BankAccountDTO bankAccountDTO, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {


        try {
            bankAccountService.addBankAccount(userDetails.getUsername(), bankAccountDTO);

        } catch (DataAlreadyExistsException | DataMissingException e) {
            redirectAttributes.addFlashAttribute("errors", List.of(e.getMessage()));
        }

        return "redirect:/user/extransfer";
    }

    @PostMapping("/extransfer/deleteBankAccount")
    public String deleteBankAccount(@RequestParam String iban) {

        bankAccountService.deleteBankAccountByIban(iban);

        return "redirect:/user/extransfer";
    }


    @PostMapping("/extransfer/addExternalTransfer")
    public String doExternalTransfer(@ModelAttribute ExternalTransferDTO externalTransferDTO, @AuthenticationPrincipal UserDetails userDetails) {

        externalTransferDTO.setEmailUser(userDetails.getUsername());

        transferService.doExternalTransfer(externalTransferDTO);



        return "redirect:/user/extransfer";
    }
}
