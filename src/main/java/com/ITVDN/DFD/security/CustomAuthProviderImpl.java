package com.ITVDN.DFD.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomAuthProviderImpl implements AuthenticationProvider {

    private final CustomUserDetailsServiceImpl userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthProviderImpl(CustomUserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (Objects.isNull(userDetailsService)) {
            throw new InternalAuthenticationServiceException("User service is null");
        }
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new AuthenticationCredentialsNotFoundException
                    (CustomUserDetailsServiceImpl.ERROR);
        }
        if (passwordEncoder.matches(password, user.getPassword()))
            return new UsernamePasswordAuthenticationToken(user,
                    authentication.getCredentials(), user.getAuthorities());
        else {
            throw new AuthenticationServiceException(CustomUserDetailsServiceImpl.ERROR);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
