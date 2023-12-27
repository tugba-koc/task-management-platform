package dev.tugba.taskapp.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tugba.taskapp.auth.config.abstracts.JwtService;
import dev.tugba.taskapp.business.abstracts.UserRequestService;
import dev.tugba.taskapp.business.responses.GetAllUserDataResponse;
import dev.tugba.taskapp.core.utilities.mappers.ModelMapperService;
import dev.tugba.taskapp.dataAccess.abstracts.UserRepository;

@Service
public class UserRequestManager implements UserRequestService {
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ModelMapperService modelMapperService;

    public UserRequestManager(UserRepository userRepository) {
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