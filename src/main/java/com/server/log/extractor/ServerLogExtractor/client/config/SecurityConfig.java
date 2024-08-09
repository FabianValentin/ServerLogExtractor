package com.server.log.extractor.ServerLogExtractor.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Base64;

@Configuration
public class SecurityConfig {
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String encodedAdminPassword;

    @Value("${user.username}")
    private String userUsername;

    @Value("${user.password}")
    private String encodedUserPassword;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/main.css").permitAll()
                        .requestMatchers( "/favicon.ico").permitAll()
                        .requestMatchers("/gs-guide-websocket/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/servers", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                               .username(userUsername)
                               .password(new String(Base64.getDecoder().decode(encodedUserPassword)))
                               .roles("USER")
                               .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                                .username(adminUsername)
                                .password(new String(Base64.getDecoder().decode(encodedAdminPassword)))
                                .roles("ADMIN")
                                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
