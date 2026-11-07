// Sovelluksen kirjautumisen ja käyttöoikeuksien asetukset.
package com.tripplanner.dev.config;

// Spring Securityn asetusten rakentamiseen tarvittavat luokat.
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Määrittelee sovelluksen turvallisuusasetukset.
@Configuration 
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    // Luo salasanojen salaamiseen käytettävän BCrypt-encoderin.
    @Bean 
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Määrittelee sivujen käyttöoikeudet ja kirjautumisen toiminnan.
    @Bean 
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests( authorize -> authorize 
                // Rekisteröinti ja staattiset tiedostot ovat julkisia.
                .requestMatchers("/auth/signup", "/css/**", "/js/**").permitAll() // Public signup and static assets.
                // Kaikki muut sivut vaativat kirjautumisen.
				.anyRequest().authenticated()                 // All other paths must be authenticated.
			)                                             // If you don't give loginPage
        // Määrittelee kirjautumissivun ja onnistuneen kirjautumisen kohteen.
		.formLogin( formlogin -> formlogin                    // your application will use the
			.loginPage("/auth/login")                          // spring boot default login page.
			.loginProcessingUrl("/auth/login")
			.failureUrl("/auth/login?error")
			.defaultSuccessUrl("/home", true)      // <-- Tells where to go after
			.permitAll()                                  // successful login.
		)
        // Määrittelee uloskirjautumisen toiminnan.
		.logout( logout -> logout
			.invalidateHttpSession(true)
            .logoutSuccessUrl("/auth/login?logout")
			.permitAll()
		);
		return http.build();
	}
}
