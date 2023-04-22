package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.DTO.entity.UserDTO;
import com.cs4523groupb11.Motify.entities.Role;
import com.cs4523groupb11.Motify.entities.enums.RoleType;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.DTO.response_non_entity.LoginResponse;
import com.cs4523groupb11.Motify.repositories.RoleRepository;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.security.UserDetailsImpl;
import com.cs4523groupb11.Motify.services.AuthService;
import com.cs4523groupb11.Motify.security.JwtTokenUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtTokenUtility jwtTokenUtility;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder encoder,
                           JwtTokenUtility jwtTokenUtility) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtTokenUtility = jwtTokenUtility;
    }


    public Optional<LoginResponse> authenticateUser(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtility.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Optional.of(new LoginResponse(jwt, new UserDTO(userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), "")));
    }

    @Transactional
    public Optional<LoginResponse> registerUser(String username, String email, String password, List<String> strRoles) {
        if (userRepository.existsByEmail(email)) {
            return Optional.empty();
        }
        User user = new User(username, encoder.encode(password), email);
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Optional<Role> userRole = roleRepository.findByName(RoleType.ROLE_USER);
            if (userRole.isEmpty()){
                return Optional.empty();
            }
            roles.add(userRole.get());
        } else {
            for (String s: strRoles){
                switch (s) {
                    case "admin":
                        Optional<Role> adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN);
                        if (adminRole.isEmpty()){
                            return Optional.empty();
                        }
                        roles.add(adminRole.get());
                        break;
                    default:
                        Optional<Role> userRole = roleRepository.findByName(RoleType.ROLE_USER);
                        if (userRole.isEmpty()){
                            return Optional.empty();
                        }
                        roles.add(userRole.get());
                }
            }
        }

        user.setRoles(roles);
        userRepository.saveAndFlush(user);
        return authenticateUser(email, password);
    }
}
