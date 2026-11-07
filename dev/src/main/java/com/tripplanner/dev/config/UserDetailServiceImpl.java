// Käyttäjien tunnistautumiseen liittyvät palvelut.
package com.tripplanner.dev.config;

// Spring Securityn käyttäjätietojen käsittelyyn tarvittavat luokat.
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tripplanner.dev.domain.User;
import com.tripplanner.dev.domain.UserRepository;

// Palvelu, jonka avulla Spring Security hakee käyttäjän kirjautumista varten.
@Service 
public class UserDetailServiceImpl implements UserDetailsService {

    // Käyttäjien tietokantahakuun käytettävä repository.
    private final UserRepository repository;

    // Spring välittää repositoryn palvelulle.
    public UserDetailServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    // Hakee käyttäjän käyttäjänimen perusteella.
    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        // Jos käyttäjää ei löydy, kirjautuminen keskeytetään virheeseen.
        User curuser = repository.findByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("User not found: " + username));
       
        // Muodostaa Spring Securityn käyttämän käyttäjätiedon.
        return new org.springframework.security.core.userdetails.User(
            curuser.getUsername(),
            curuser.getPasswordHash(),
            AuthorityUtils.createAuthorityList(curuser.getRole()));
    }
}
