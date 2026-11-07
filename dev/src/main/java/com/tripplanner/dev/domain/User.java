// Käyttäjän tietokantamalli.
package com.tripplanner.dev.domain;
// Käyttäjän luontiajan käsittelyyn tarvittava aikaluokka.
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

// JPA:n avulla luokka yhdistetään tietokantatauluun.
@Entity
@Table (name = "users")
public class User {

    // Tietokannan automaattisesti luoma käyttäjän tunniste.
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "passwordhash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role;

    @Column(name = "email")
    private String email;
 
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // JPA tarvitsee parametrittoman konstruktorin.
    protected User() {
    }

    // Luo uuden tavallisen käyttäjän.
    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        // Uudelle käyttäjälle annetaan turvallinen oletusrooli.
        this.role = "USER";
    }

    // Asettaa luontiajan ennen ensimmäistä tallennusta.
    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPasswordHash() {
        return passwordHash;
    }
 
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
 
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role + "]";
    }

    

    

}
    // Yksilöllinen käyttäjänimi.
    // Salasanan salattu arvo.
    // Käyttäjän käyttöoikeusrooli.
    // Käyttäjän sähköpostiosoite.
    // Käyttäjän luontiaika.
