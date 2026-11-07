// Lentojen tietokantahaut.
package com.tripplanner.dev.domain;

// Repositoryn ja kyselyiden tarvitsemat luokat.
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// Spring Data JPA luo tietokantahaut tämän rajapinnan perusteella.
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    // Hakee kaikki lennot annetun lähtöpaikan ja määränpään perusteella.
    List<Flight> findByOriginIgnoreCaseAndDestinationIgnoreCase(String origin, String destination);

    // Hakee lähtöpaikkojen yksilöllisen listan automaattista täydennystä varten.
    @Query("select distinct f.origin from Flight f where f.origin is not null order by f.origin")
    List<String> findDistinctOrigins();

    // Hakee määränpäiden yksilöllisen listan automaattista täydennystä varten.
    @Query("select distinct f.destination from Flight f where f.destination is not null order by f.destination")
    List<String> findDistinctDestinations();
}
