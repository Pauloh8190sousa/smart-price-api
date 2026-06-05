package com.phsousa.smart_price_api.security;


import com.phsousa.smart_price_api.dto.request.LoginRequestDTO;
import com.phsousa.smart_price_api.dto.request.RegisterRequestDTO;
import com.phsousa.smart_price_api.dto.response.AuthResponseDTO;
import com.phsousa.smart_price_api.dto.response.PermissionResponseDTO;
import com.phsousa.smart_price_api.dto.response.RoleResponseDTO;
import com.phsousa.smart_price_api.dto.response.UserResponseDTO;
import com.phsousa.smart_price_api.entity.Role;
import com.phsousa.smart_price_api.entity.User;
import com.phsousa.smart_price_api.exception.EmailAlreadyExistsException;
import com.phsousa.smart_price_api.exception.ResourceNotFoundException;
import com.phsousa.smart_price_api.repository.RoleRepository;
import com.phsousa.smart_price_api.repository.UserRepository;
import com.phsousa.smart_price_api.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository  roleRepository;
    private final EmailService emailService;

    public UserResponseDTO register(RegisterRequestDTO request) {

        String email = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        Role role = roleRepository.findByNameWithPermissions("ROLE_USER")
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role ROLE_USER não encontrada")
                );

        User user = User.builder()
                .name(request.getName().trim())
                .email(email)
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .active(true)
                .build();

        User savedUser = userRepository.save(user);

        emailService.sendWelcomeEmail(
                savedUser.getEmail(),
                savedUser.getName()
        );

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    public AuthResponseDTO login(LoginRequestDTO request) {

        String email = request.getEmail().trim().toLowerCase();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmailWithRolesAndPermissions(email)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Inconsistência na autenticação"
                        )
                );

        String token = jwtService.generateToken(user);

        UserResponseDTO userDTO = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

        Set<RoleResponseDTO> roles = user.getRoles()
                .stream()
                .map(role -> new RoleResponseDTO(
                        role.getName(),
                        role.getPermissions()
                                .stream()
                                .map(permission ->
                                        new PermissionResponseDTO(permission.getName())
                                )
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toSet());

        return new AuthResponseDTO(
                token,
                userDTO,
                roles
        );
    }
}