package com.tripplanner.dev.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {

    // Lennon yksilöllinen tunniste.
    @Id
    private Integer id;

    // Lentoyhtiön nimi.
    @Column(nullable = false, length = 100)
    private String airline;

    // Lennon tunnus, esimerkiksi AY123.
    @Column(nullable = false, length = 20)
    private String flightcode;

    // Lennon lähtöpaikka.
    @Column(nullable = false)
    private String origin;

    // Lennon määränpää.
    @Column(nullable = false)
    private String destination;

    // Suunniteltu lähtöaika.
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    // Suunniteltu saapumisaika.
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    // Lennon hinta euroina.
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // JPA tarvitsee parametrittoman konstruktorin.
    protected Flight() {
    }

    public String getAirline() {
        return airline;
    }

    public String getFlightcode() {
        return flightcode;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
