package com.recruiter.backend.domain.service.auth;

import com.recruiter.backend.domain.dtos.auth.AuthRequest;
import com.recruiter.backend.domain.dtos.auth.AuthResponse;
import com.recruiter.backend.domain.dtos.auth.RegisterRequest;
import com.recruiter.backend.infraestructure.crud_repository.UserCrudRepository;
import com.recruiter.backend.infraestructure.entitys.UserEntity;
import com.recruiter.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserCrudRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        userRepository.findByUsername(request.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("User with username " + request.getUsername() + " already exists.");
        });

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(); // Should not happen if authentication was successful
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
