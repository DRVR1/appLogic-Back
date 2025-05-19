package com.drvr1.applogic.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .cors(co -> co
                        .disable())
                .csrf(cs -> cs
                        .disable())
                .formLogin(formLogin -> formLogin
                        .disable()
                )
                .securityMatcher("/**")
                .sessionManagement(session -> session
                        . sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(HttpMethod.OPTIONS,"/api/createAppVersion").permitAll()
                        .requestMatchers("/api/createAppVersion").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.OPTIONS,"/api/deleteAppVersion").permitAll()
                        .requestMatchers("/api/deleteAppVersion").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.OPTIONS,"/api/createModdedApp").permitAll()
                        .requestMatchers("/api/createModdedApp").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.OPTIONS,"/api/deleteModdedApp").permitAll()
                        .requestMatchers("/api/deleteModdedApp").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.OPTIONS,"/api/updateModdedApp").permitAll()
                        .requestMatchers("/api/updateModdedApp").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.OPTIONS,"/api/createUser").permitAll()

                        .anyRequest().permitAll()
                )

                .rememberMe(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

}