package com.tripplanner.dev.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findByOriginIgnoreCaseAndDestinationIgnoreCase(String origin, String destination);

    @Query("select distinct f.origin from Flight f where f.origin is not null order by f.origin")
    List<String> findDistinctOrigins();

    @Query("select distinct f.destination from Flight f where f.destination is not null order by f.destination")
    List<String> findDistinctDestinations();
}
