package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.services.IUserService;
import com.openclassroom.paymybuddy.web.exception.DataAlreadyExistsException;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class RelationController {

    @Autowired
    private IUserService userService;

    @GetMapping("/relation")
    public String relationPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        model.addAttribute("relations", userService.relationListEmail(userDetails.getUsername()));


        return "relation";
    }

    @PostMapping("/relation/addBuddy")
    public String addRelation(@RequestParam String emailBuddy, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {


        try {
            userService.addRelation(userDetails.getUsername(), emailBuddy);

        } catch (DataAlreadyExistsException | DataNotFoundException | SQLException e) {
            redirectAttributes.addFlashAttribute("errors", List.of(e.getMessage()));
        }

        return "redirect:/user/relation";
    }

    @PostMapping("/relation/deleteRelation")
    public String deleteRelation(@RequestParam Integer id) {

        userService.deleteRelationById(id);

        return "redirect:/user/relation";
    }




















}
