package com.tripplanner.dev.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/signin")
    public String signin(){
        return "signin.html";
    }

    @RequestMapping("/signup")
    public String signupPage(){
        return "signup.html";
    }

}
