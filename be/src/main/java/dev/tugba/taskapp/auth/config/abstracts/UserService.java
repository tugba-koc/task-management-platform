package dev.tugba.taskapp.auth.config.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService {
    public UserDetailsService userDetailsService();
}
