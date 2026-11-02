package com.tripplanner.dev.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
    @NotBlank 
        @Size(min = 3, max = 30)
        String username,

        @NotBlank 
        @Size 
        String password
) {
    
    
}
