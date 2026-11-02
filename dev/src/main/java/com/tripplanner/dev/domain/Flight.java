package com.tripplanner.dev.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    protected Flight() {
        // Required by JPA
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}
