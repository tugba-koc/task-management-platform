package dev.tugba.taskapp.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tugba.taskapp.auth.config.abstracts.JwtService;
import dev.tugba.taskapp.business.abstracts.UserRequestService;
import dev.tugba.taskapp.business.responses.GetAllUserDataResponse;
import dev.tugba.taskapp.core.utilities.mappers.ModelMapperService;
import dev.tugba.taskapp.dataAccess.abstracts.UserRepository;
import dev.tugba.taskapp.entities.concretes.User;
import dev.tugba.taskapp.helper.Helper;

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
        String token = Helper.extractToken(bearerToken);
        String email = this.jwtService.extractUsername(token);
        User user = this.userRepository.findByEmail(email).orElseThrow();
        GetAllUserDataResponse getAllUserDataResponse = this.modelMapperService.forResponse().map(user,GetAllUserDataResponse.class);
        return getAllUserDataResponse;
    }
}