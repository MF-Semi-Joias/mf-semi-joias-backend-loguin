package mf.semi.joias.backend.loguin.secutity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
        public class SecurityConfig {

// SecurityConfig.java
            @Bean
            public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
                http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                    )
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
            }

            @Bean
            public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
            }

            // Bean para senha em texto puro (apenas para testes)
            @Bean
            public PasswordEncoder passwordEncoder() {
                return NoOpPasswordEncoder.getInstance();
            }
        }