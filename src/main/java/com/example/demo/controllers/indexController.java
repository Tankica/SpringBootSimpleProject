package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    @GetMapping("/")
    public String showIndexPage(){
        return "index";
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return "views/loginForm";
    }
}
