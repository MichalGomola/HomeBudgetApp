package com.mg.HomeBudgetApp.config;

import ch.qos.logback.core.encoder.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUsers() {
        // using {}
        UserDetails user = User.withUsername("user")
                .password(bcryptPasswordEncoder().encode("user"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain matchersFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) ->
                        authz
                                .requestMatchers("/styles.css", "/login", "/")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(form -> form
                        .failureUrl("/login")
                        .successForwardUrl("/"))
                        .logout(logout -> logout
                                .logoutUrl("/logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .logoutSuccessUrl("/")

        );
        return http.build();
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
