package com.tripplanner.dev.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tripplanner.dev.config.UserService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;;

@Controller 
@RequestMapping("/auth")
public class AuthController {

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
    public String signupPage(@Valid @ModelAttribute SignupRequest request,
                            BindingResult result,
                             Model model){
        if (result.hasErrors()) {
        model.addAttribute("error", "Invalid username or password");
        return "signup";
        }                        
        
        try{
            userService.register(request.username(),request.password());
        } catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
        return "redirect:/auth/login";
    }


}
