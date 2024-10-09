package com.ITVDN.DFD.config;

import com.ITVDN.DFD.entities.Role;
import com.ITVDN.DFD.entities.User;
import com.ITVDN.DFD.repositories.UserRepository;
import com.ITVDN.DFD.services.Interfaces.IMyUserService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
@Component
public class DefaultDataConfig {
    private final UserRepository userRepository;
    private final IMyUserService userService;

    private final PasswordEncoder passwordEncoder;

    public DefaultDataConfig(UserRepository userRepository, IMyUserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        createDefaultUsers();
    }
    private void createDefaultUsers() {
        if (userRepository.findByUsername("john").isEmpty()) {
            userService.createUser(
                    User.builder().username("john").password(passwordEncoder
                            .encode("123")).build()
            );
        }

        if (userRepository.findByUsername("jane").isEmpty()) {
            userService.createUser(
                    User.builder().username("jane").password(passwordEncoder
                            .encode("123")).build()
            );
        }

        if (userRepository.findByUsername("admin").isEmpty()) {
            userService.createUser(
                    User.builder().username("admin").password(passwordEncoder
                                    .encode("admin"))
                            .roles(new HashSet<>(Collections.singletonList(Role.ADMIN)))
                            .build()
            );
        }

        if (userRepository.findByUsername("moder").isEmpty()) {
            userService.createUser(
                    User.builder().username("moder").password(passwordEncoder
                                    .encode("moder"))
                            .roles(new HashSet<>(Collections.singletonList(Role.MODERATOR)))
                            .build()
            );
        }

        if (userRepository.findByUsername("umoderator").isEmpty()) {
            userService.createUser(
                    User.builder().username("umoderator").password(passwordEncoder
                                    .encode("12345"))
                            .roles(new HashSet<>(Arrays.asList(Role.ADMIN, Role.MODERATOR)))
                            .build()
            );
        }
    }

    @PostConstruct
    public void init() {
        createDefaultUsers();
    }
}
