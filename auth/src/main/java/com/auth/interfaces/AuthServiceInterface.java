package com.auth.interfaces;

import com.auth.model.Auth;

public interface AuthServiceInterface {
    public Long saveAuth(Auth user);
    void deleteAuth(Long id);
    public String generateToken(String email);
    public void validateToken(String token);
}
