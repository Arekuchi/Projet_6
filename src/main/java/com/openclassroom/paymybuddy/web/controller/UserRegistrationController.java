package com.openclassroom.paymybuddy.web.controller;

import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    // field


    private IUserService userService;

    public UserRegistrationController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm() {

        return "registration";
    }

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {

        return new UserRegistrationDTO();
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDTO userRegistrationDTO) {

        userService.save(userRegistrationDTO);

        return "redirect:/registration?success";
    }

}
