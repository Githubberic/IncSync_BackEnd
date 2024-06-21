package com.auth.service;

import com.auth.interfaces.AuthServiceInterface;
import com.auth.model.Auth;
import com.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements AuthServiceInterface {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public Long saveAuth(Auth user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Auth savedUser = authRepository.save(user);
        return savedUser.getAuthId();
    }

    public String generateToken(String email)
    {
        Optional<Auth> authOptional = authRepository.findByEmail(email);

        if (authOptional.isPresent()) {
            Auth auth = authOptional.get();
            List<String> claims = new ArrayList<>();

            if (auth.getIsAdmin()) {
                claims.add("ADMIN");
            }

            return jwtService.generateToken(email, claims);
        } else {
            // Handle the case where the user doesn't exist
            return null; // Or throw an exception or handle it according to your application's logic
        }

    }

    public void validateToken(String token)
    {
        jwtService.validateToken(token);
    }

    public void deleteAuth(Long id)
    {
        Optional<Auth> user = authRepository.findById(id);
        if(user.isPresent()) {
            authRepository.deleteById(id);
        }
    }
}
