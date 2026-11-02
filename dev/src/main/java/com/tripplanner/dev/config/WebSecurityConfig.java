package com.tripplanner.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Bean 
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean 
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests( authorize -> authorize 
                .requestMatchers("/auth/signup", "/css/**", "/js/**").permitAll() // Public signup and static assets.
				.anyRequest().authenticated()                 // All other paths must be authenticated.
			)                                             // If you don't give loginPage
		.formLogin( formlogin -> formlogin                    // your application will use the
			.loginPage("/auth/login")                          // spring boot default login page.
			.loginProcessingUrl("/auth/login")
			.failureUrl("/auth/login?error")
			.defaultSuccessUrl("/home", true)      // <-- Tells where to go after
			.permitAll()                                  // successful login.
		)
		.logout( logout -> logout
			.invalidateHttpSession(true)
            .logoutSuccessUrl("/auth/login?logout")
			.permitAll()
		);
		return http.build();
	}
}
