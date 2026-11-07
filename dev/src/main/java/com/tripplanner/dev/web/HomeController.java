package com.tripplanner.dev.web;

import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/flights")
    public String flights(@RequestParam String departure,
                          @RequestParam String destination,
                          Model model) {
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("flights",
                flightRepository.findByOriginIgnoreCaseAndDestinationIgnoreCase(departure, destination));
        return "flights.html";
    }

    @RequestMapping("/profile")
    public String profile(){
        return "profile.html";
    }
}
