package com.shop.shopimage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                //Доступ только для не зарегистрированных пользователей
//                .requestMatchers("/registration",
//                        "/",
//                        "/login",
//                        "/h2-console/**")
//                .permitAll()
//                .requestMatchers( "/css/**", "/templates/**").permitAll()
//                //Доступ только для пользователей с ролью Администратор
//                .requestMatchers("/news").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/**").hasRole("ADMIN")
//
//                //Все остальные страницы требуют аутентификации
//                .anyRequest().authenticated()
//                .and()
//                //Настройка для входа в систему
//                .formLogin()
//                .loginPage("/login")
//                //Перенарпавление на главную страницу после успешного входа
//                .defaultSuccessUrl("/", true)
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .logoutSuccessUrl("/")
//                .and().build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                .authorizeHttpRequests((requests) -> requests


                        .requestMatchers("/registration",
                                "/",
                                "/login",
                                "/h2-console/**")
                        .permitAll()
                        .requestMatchers( "/css/**", "/templates/**").permitAll()
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
                ).csrf(AbstractHttpConfigurer::disable)
                        //для открытия h2 console
                        .headers().frameOptions().disable();

        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("useradmin")
//                        .password("password")
//                        .roles("ADMIN")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }


}