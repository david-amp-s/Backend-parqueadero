package com.parqueadero.parkplace.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.parqueadero.parkplace.security.filter.SecurityFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> {
                })
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(rq -> {
                    rq.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    rq.requestMatchers("/api/dashboard-admin").permitAll();
                    rq.requestMatchers("/api/clientes/**").hasAnyRole("ADMIN", "EMPLEADO");
                    rq.requestMatchers("/api/usuarios/**").hasAnyRole("ADMIN");
                    rq.requestMatchers("/api/vehiculos/**").hasAnyRole("ADMIN", "EMPLEADO");
                    rq.requestMatchers(HttpMethod.POST, "/api/espacios").hasAnyRole("ADMIN");
                    rq.requestMatchers("/api/espacios/**").hasAnyRole("ADMIN", "EMPLEADO");
                    rq.requestMatchers("/api/tarifas/**").hasAnyRole("ADMIN");
                    rq.requestMatchers(HttpMethod.DELETE, "/api/ingresos/**").hasAnyRole("ADMIN");
                    rq.requestMatchers("/api/ingresos/**").hasAnyRole("ADMIN", "EMPLEADO");
                    rq.requestMatchers("/api/salidas/**").hasAnyRole("ADMIN", "EMPLEADO");
                    rq.requestMatchers("/api/formas_pago/**").hasAnyRole("ADMIN");
                    rq.requestMatchers("/api/detalle_pagos/**").hasAnyRole("ADMIN", "EMPLEADO");
                    rq.requestMatchers("/api/facturas/**").hasAnyRole("ADMIN", "EMPLEADO");
                    rq.anyRequest().authenticated();

                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuracion) throws Exception {
        return configuracion.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
