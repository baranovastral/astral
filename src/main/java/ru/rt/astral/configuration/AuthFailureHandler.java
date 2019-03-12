package ru.rt.astral.configuration;

import org.springframework.security.web.authentication
        .SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    
}
