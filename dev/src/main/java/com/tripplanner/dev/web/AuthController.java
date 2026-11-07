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

    // Palvelu, joka käsittelee käyttäjien rekisteröinnin.
    private final UserService userService;

    // Spring välittää UserService-riippuvuuden konstruktorin kautta.
    public AuthController(UserService userService){
        this.userService = userService;
    }

    // Näyttää kirjautumissivun.
    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    // Näyttää uuden käyttäjän rekisteröitymissivun.
    @RequestMapping("/signup")
    public String signupForm(){
        return "signup.html";
    }

    // Vastaanottaa rekisteröitymislomakkeen ja luo uuden käyttäjän.
    @PostMapping("/signup")
    public String signupPage(@Valid @ModelAttribute SignupRequest request,
                            BindingResult result,
                             Model model){
        // Tarkistaa lomakkeen käyttäjänimen ja salasanan.
        if (result.hasErrors()) {
            model.addAttribute("error", "Invalid username or password");
            return "signup";
        }                        
        
        // Yrittää tallentaa uuden käyttäjän tietokantaan.
        try{
            userService.register(request.username(),request.password());
        } catch (IllegalArgumentException e){
            // Näyttää käyttäjälle rekisteröinnin aikana syntyneen virheen.
            model.addAttribute("error", e.getMessage());
            return "signup";
        }

        // Onnistuneen rekisteröinnin jälkeen siirrytään kirjautumissivulle.
        return "redirect:/auth/login";
    }


}
