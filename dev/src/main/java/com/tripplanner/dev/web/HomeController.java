// Etusivun ja lentohakujen HTTP-käsittelijä.
package com.tripplanner.dev.web;

// Sijaintien järjestämiseen tarvittava kokoelma.
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tripplanner.dev.domain.FlightRepository;

// Spring MVC:n ohjainluokka.
@Controller 
public class HomeController {

    // Lentojen tietokantahakuihin käytettävä repository.
    private final FlightRepository flightRepository;

    // Spring välittää repositoryn ohjaimelle.
    public HomeController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Näyttää etusivun ja toimittaa sijaintitiedot hakukenttien täydennykseen.
    @RequestMapping({"/","/home"})
    public String home(Model model){
        // TreeSet poistaa duplikaatit ja järjestää sijainnit aakkosittain.
        TreeSet<String> locations = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        locations.addAll(flightRepository.findDistinctOrigins());
        locations.addAll(flightRepository.findDistinctDestinations());
        model.addAttribute("flightLocations", locations);
        // Palauttaa etusivun näkymän.
        return "home.html";
    }

    // Hakee tietokannasta käyttäjän valitseman reitin lennot.
    @GetMapping("/flights")
    public String flights(@RequestParam String departure,
                          @RequestParam String destination,
                          Model model) {
        // Välittää hakuehdot ja tulokset Thymeleaf-näkymälle.
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("flights",
                flightRepository.findByOriginIgnoreCaseAndDestinationIgnoreCase(departure, destination));
        // Palauttaa lentojen tulossivun.
        return "flights.html";
    }

    // Näyttää käyttäjän profiilisivun.
    @RequestMapping("/profile")
    public String profile(){
        return "profile.html";
    }
}
