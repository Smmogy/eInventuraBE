package com.springboot.einventura.security.config.auth;

import com.springboot.einventura.model.bean.Roles;
import com.springboot.einventura.model.bean.User;
import com.springboot.einventura.model.repository.UserRepository;
import com.springboot.einventura.model.service.UserService;
import com.springboot.einventura.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final String USER_EXIST_ERROR_MASSAGE = "User already exists";

    public String userExist(RegisterRequest request){
        if (repository.findByEmail(request.getEmail()).isPresent()){
           return USER_EXIST_ERROR_MASSAGE;
        }
        return "";
    }
    public AuthenticationResponse register(RegisterRequest request) {
        if(!userExist(request).equals(USER_EXIST_ERROR_MASSAGE)) {
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Roles.USERS)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            return AuthenticationResponse.builder().build();
        }
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
