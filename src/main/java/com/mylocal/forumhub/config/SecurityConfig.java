package com.mylocal.forumhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Permite cadastro de clientes e usuários
                        .requestMatchers(HttpMethod.POST, "/clientes", "/usuarios").permitAll()

                        // Permite listagem e busca por ID de clientes e usuários
                        .requestMatchers(HttpMethod.GET, "/clientes/**", "/usuarios/**").permitAll()

                        // Permite desativar clientes e usuários via DELETE
                        .requestMatchers(HttpMethod.DELETE, "/clientes/**", "/usuarios/**").permitAll()

                        // Permite ativar clientes (PUT /clientes/{id}/ativar)
                        .requestMatchers(HttpMethod.PUT, "/clientes/*/ativar", "/usuarios/*/ativar").permitAll()

                        // Qualquer outra requisição exige autenticação
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
