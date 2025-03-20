package com.pragma.user.infrastructure.security.service.impl;

import com.pragma.user.infrastructure.exception.UserNotFoundException;
import com.pragma.user.infrastructure.helper.constants.ExceptionConstants;
import com.pragma.user.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user.infrastructure.out.jpa.repository.UserRepository;
import com.pragma.user.infrastructure.security.service.IUserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAuthenticationService implements IUserAuthenticationService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean login(String email, String password) {
        Authentication authentication = this.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(ExceptionConstants.BAD_CREDENTIALS));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRole().getName())));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = this.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException(ExceptionConstants.BAD_CREDENTIALS);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException(ExceptionConstants.BAD_CREDENTIALS);
        }

        return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
    }
}
