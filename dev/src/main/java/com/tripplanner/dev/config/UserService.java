// Käyttäjien rekisteröinnin käsittelevä palvelu.
package com.tripplanner.dev.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tripplanner.dev.domain.User;
import com.tripplanner.dev.domain.UserRepository;

@Service 
public class UserService {
    // Käyttäjien tallentamiseen käytettävä repository.
    private final UserRepository userRepository;

    // Salasanojen turvalliseen salaamiseen käytettävä encoder.
    private final PasswordEncoder passwordEncoder;

    // Spring välittää tarvittavat riippuvuudet konstruktorille.
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Luo uuden käyttäjän ja tallentaa sen tietokantaan.
    public void register(String username, String rawPassword) {
        // Sama käyttäjänimi ei saa olla käytössä useammalla käyttäjällä.
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already taken");
        }
        // Salasana salataan ennen käyttäjän tallentamista.
        User user = new User(username, passwordEncoder.encode(rawPassword));
        userRepository.save(user);
    }

}
