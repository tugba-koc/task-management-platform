package dev.tugba.taskapp.auth.config.abstracts;

import org.springframework.security.core.userdetails.UserDetails;

import dev.tugba.taskapp.entities.concretes.User;


public interface JwtService {
    public String generateToken(User user);
    public String extractUsername(String token);
    public boolean isTokenValid(String token, UserDetails userDetails);
    public int extractUserId(String token);
    public boolean isTokenExpired(String token);
}
