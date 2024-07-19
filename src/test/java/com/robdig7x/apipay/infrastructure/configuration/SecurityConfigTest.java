package com.robdig7x.apipay.infrastructure.configuration;

import com.robdig7x.apipay.infrastructure.security.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@EnableWebSecurity
public class SecurityConfigTest {

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticationManager() throws Exception {
        AuthenticationManager authenticationManager = securityConfig.authenticationManager();
        assertNotNull(authenticationManager);

        DaoAuthenticationProvider provider = (DaoAuthenticationProvider) ((ProviderManager) authenticationManager).getProviders().get(0);
        assertNotNull(provider);
    }

    @Test
    public void testUserDetailsService() {
        assertNotNull(securityConfig.userDetailsService());
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assert(passwordEncoder instanceof BCryptPasswordEncoder);
    }
}
