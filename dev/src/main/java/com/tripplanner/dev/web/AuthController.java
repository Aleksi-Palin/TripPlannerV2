package com.tripplanner.dev.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tripplanner.dev.config.UserService;
import com.tripplanner.dev.domain.UserRepository;

import org.springframework.ui.Model;;

@Controller 
@RequestMapping("/auth")
public class AuthController {

    private UserRepository uRepository;
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @RequestMapping("/signup")
    public String signupForm(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public String signupPage(@RequestParam String username,
                             @RequestParam String password,
                             Model model){
        try{
            userService.register(username,password);
        } catch (IllegalArgumentException e){
            model.addAttribute("error: ", e.getMessage());
            return "signup";
        }
        return "redirect:/login";
    }


}
