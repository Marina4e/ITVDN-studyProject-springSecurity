package com.ITVDN.DFD.security;

import com.ITVDN.DFD.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

    private final CustomAuthProviderImpl customAuthProvider;
    private final CustomUserDetailsServiceImpl userDetailsService;
    private final String SECRET = "SuperSecretKey";

    @Autowired
    public WebSecurityConfiguration(CustomAuthProviderImpl customAuthProvider,
                                    CustomUserDetailsServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.customAuthProvider = customAuthProvider;
        this.userDetailsService = userService;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/statics/**", "/index*", "/login", "/access-denied").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/user/**").hasRole(Role.USER.name())
                        .requestMatchers(HttpMethod.GET, "/admin/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/admin/**").hasRole(Role.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // страница логина
                        .loginProcessingUrl("/perform_login")  // URL для обработки формы логина
                        .defaultSuccessUrl("/", true)  // перенаправление после успешного логина
                        .failureUrl("/login?error=true")  // страница ошибки
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // URL для выхода
                        .logoutSuccessUrl("/login?logout=true")  // перенаправление после выхода
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")  // Страница ошибки доступа
                )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    RememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices(SECRET, this.userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(this.customAuthProvider);
        return authenticationManagerBuilder.build();
    }
}
