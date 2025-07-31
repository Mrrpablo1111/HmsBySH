package com.hms.appointment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 12); // Explicitly set strength
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception{
        return builder.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(authorize ->
//                authorize.requestMatchers("/**").permitAll()
//                         .anyRequest().authenticated()
//        );
//        http.csrf(AbstractHttpConfigurer::disable);
//        return http.build();

//        http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth.requestMatchers(request -> "SECRET".equals(request.getHeader("X-Secret-Key"))).permitAll().anyRequest().denyAll());
//        return http.build();

        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/register").permitAll()  // Allow registration
                        .requestMatchers("/user/login").permitAll()     // Allow login
                        .requestMatchers(request -> "SECRET".equals(request.getHeader("X-Secret-Key")))
                        .permitAll()
                        .anyRequest().authenticated()  // All other requests require auth
                );
        return http.build();
    }
}