package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.*;


@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "views/registerForm";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model){
        System.out.println("RESULT" + user.getEmail() + " " + user.getName());
        if(bindingResult.hasErrors()){
            System.out.println("ERRRORRRR");
            return "views/registerForm";
        }
        if(userService.isUserPresent(user.getEmail())){
            System.out.println("ERRRORRRR 1");
            model.addAttribute("exist", true);
            return "views/registerForm";
        }
            userService.createUser(user);
            return "views/success";


    }
}
