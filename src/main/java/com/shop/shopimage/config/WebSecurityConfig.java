package com.shop.shopimage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests


                        .requestMatchers("/registration",
                                "/",
                                "/login",
                                "/h2-console/**")
                        .permitAll()
                        .requestMatchers("/css/**", "/templates/**").permitAll()
                        //Доступ только для пользователей с ролью Администратор
                        .requestMatchers("/news").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                ).csrf(AbstractHttpConfigurer::disable
                )
                //для открытия h2 console
                .headers().frameOptions().disable();

        return http.build();
    }

}