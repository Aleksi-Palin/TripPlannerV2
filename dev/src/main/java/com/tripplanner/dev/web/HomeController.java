package com.tripplanner.dev.web;

import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tripplanner.dev.domain.FlightRepository;

@Controller 
public class HomeController {

    private final FlightRepository flightRepository;

    public HomeController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @RequestMapping({"/","/home"})
    public String home(Model model){
        TreeSet<String> locations = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        locations.addAll(flightRepository.findDistinctOrigins());
        locations.addAll(flightRepository.findDistinctDestinations());
        model.addAttribute("flightLocations", locations);
        return "home.html";
    }

    @RequestMapping("/profile")
    public String profile(){
        return "profile.html";
    }
}
