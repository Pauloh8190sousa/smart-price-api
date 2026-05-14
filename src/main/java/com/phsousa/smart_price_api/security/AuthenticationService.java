package com.phsousa.smart_price_api.security;


import com.phsousa.smart_price_api.dto.request.LoginRequestDTO;
import com.phsousa.smart_price_api.dto.request.RegisterRequestDTO;
import com.phsousa.smart_price_api.dto.response.AuthResponseDTO;
import com.phsousa.smart_price_api.dto.response.PermissionResponseDTO;
import com.phsousa.smart_price_api.dto.response.RoleResponseDTO;
import com.phsousa.smart_price_api.dto.response.UserResponseDTO;
import com.phsousa.smart_price_api.entity.Role;
import com.phsousa.smart_price_api.entity.User;
import com.phsousa.smart_price_api.repository.RoleRepository;
import com.phsousa.smart_price_api.repository.UserRepository;
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

    public UserResponseDTO register(RegisterRequestDTO request) {

        Role role = roleRepository.findByNameWithPermissions("ROLE_USER")
                .orElseThrow();

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .active(true)
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    public AuthResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmailWithRolesAndPermissions(
                request.getEmail()
        ).orElseThrow();

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