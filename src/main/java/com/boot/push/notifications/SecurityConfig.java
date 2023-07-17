package com.boot.push.notifications;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()
                .authorizeHttpRequests()
                    .mvcMatchers("/","/ws/**")
                    .permitAll()
                .and()
                .authorizeHttpRequests()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout( logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("sonia")
                .password("sonia")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
