package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DTO.InternalTransferDTO;
import com.openclassroom.paymybuddy.services.ITransferService;
import com.openclassroom.paymybuddy.services.IUserService;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import com.openclassroom.paymybuddy.web.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class InternalTransferController {

    @Autowired
    private ITransferService transferService;

    @Autowired
    private IUserService userService;

    @GetMapping("/intransfer")
    public String transferPage (Model model, @AuthenticationPrincipal UserDetails userDetails) {

        model.addAttribute("internalTransfer", new InternalTransferDTO());
        model.addAttribute("relations", userService.relationListEmail(userDetails.getUsername()));
        model.addAttribute("transfers", transferService.findInternalTransferByUser(userDetails.getUsername()));

        return "intransfer";
    }


    @PostMapping("/intransfer/internal")
    public String doInternalTransfer (@ModelAttribute InternalTransferDTO internalTransferDTO, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {

        internalTransferDTO.setEmailSender(userDetails.getUsername());

        try {

            transferService.doInternalTransfer(internalTransferDTO);

        } catch (DataNotFoundException | InvalidArgumentException | SQLException e) {
            redirectAttributes.addFlashAttribute("errors", List.of(e.getMessage()));
        }
        return "redirect:/user/intransfer";

    }







}
