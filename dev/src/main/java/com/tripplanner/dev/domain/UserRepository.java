// Käyttäjien tietokantahaut.
package com.tripplanner.dev.domain;

// Repositoryn palautustyypit.
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

// Spring Data -rajapinta käyttäjien käsittelyyn.
public interface UserRepository extends CrudRepository<User,Long> {
    // Hakee käyttäjän käyttäjänimen perusteella.
    Optional<User> findByUsername(String username);

    // Tarkistaa, onko käyttäjänimi jo käytössä.
    boolean existsByUsername(String username);
}
