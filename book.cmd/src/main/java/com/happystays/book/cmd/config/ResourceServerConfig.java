package com.happystays.book.cmd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {
    @Value("${auth.server.url}")
    private String url;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.oauth2ResourceServer(
                j -> j.jwt().jwkSetUri(url + "/auth-server/oauth2/jwks")
        ).authorizeRequests()
                .anyRequest().authenticated()
                .and().build();
    }

}