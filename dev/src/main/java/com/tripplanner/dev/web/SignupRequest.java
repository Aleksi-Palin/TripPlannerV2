// Rekisteröitymislomakkeen tiedot.
package com.tripplanner.dev.web;

// Lomakkeen syötteiden validointiin käytettävät annotaatiot.
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Lomakkeen käyttäjänimi ja salasana.
public record SignupRequest(
    // Käyttäjänimi ei saa olla tyhjä ja sen pituus on rajattu.
    @NotBlank 
        @Size(min = 3, max = 30)
        String username,

        // Salasana ei saa olla tyhjä.
        @NotBlank 
        @Size 
        String password
) {
    
    
}
