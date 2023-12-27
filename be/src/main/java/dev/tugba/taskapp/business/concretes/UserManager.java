package dev.tugba.taskapp.business.concretes;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tugba.taskapp.auth.config.concretes.JwtService;
import dev.tugba.taskapp.business.abstracts.UserService;
import dev.tugba.taskapp.business.responses.GetAllUserDataResponse;
import dev.tugba.taskapp.core.utilities.mappers.ModelMapperService;
import dev.tugba.taskapp.dataAccess.abstracts.UserRepository;

@Service
public class UserManager implements UserService {
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ModelMapperService modelMapperService;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetAllUserDataResponse getAllUserData(String bearerToken) {
        String token = extractToken(bearerToken);
        String name = this.jwtService.extractUsername(token);
        System.out.println("name >>>> " + name);
        return null;
    }

    private static String extractToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("Invalid bearer token format");
    }
}