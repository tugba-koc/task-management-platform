package dev.tugba.taskapp.business.concretes;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tugba.taskapp.auth.config.abstracts.JwtService;
import dev.tugba.taskapp.business.abstracts.UserRequestService;
import dev.tugba.taskapp.business.requests.UpdateUserEmailAddressRequest;
import dev.tugba.taskapp.business.responses.GetAllUserDataResponse;
import dev.tugba.taskapp.business.responses.UpdateUserEmailAddressResponse;
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
    public GetAllUserDataResponse getAllUserData(String bearerToken, String requestId) {
        String token = Helper.extractToken(bearerToken);
        String email = this.jwtService.extractUsername(token);

        // TODO: add an exception
        User user = this.userRepository.findByEmail(email).orElseThrow();
        GetAllUserDataResponse getAllUserDataResponse = this.modelMapperService.forResponse().map(user,GetAllUserDataResponse.class);
        
        getAllUserDataResponse.setDatetime(new Date());
        getAllUserDataResponse.setStatus("SUCCESS");
        getAllUserDataResponse.setRequestId(requestId);
        
        return getAllUserDataResponse;
    }

    @Override
    public UpdateUserEmailAddressResponse updateUserEmailAddress(String bearerToken, UpdateUserEmailAddressRequest updateUserEmailAddressRequest) {
        String token = Helper.extractToken(bearerToken);
        String email = this.jwtService.extractUsername(token);

        // TODO: add an exception
        User user = this.userRepository.findByEmail(email).orElseThrow();
        user.setEmail(updateUserEmailAddressRequest.getEmail());
        this.userRepository.save(user);

        String jwtToken = this.jwtService.generateToken(user);

        UpdateUserEmailAddressResponse updateUserEmailAddressResponse = UpdateUserEmailAddressResponse.builder()
            .email(updateUserEmailAddressRequest.getEmail())
            .status("SUCCESS")
            .datetime(LocalDateTime.now())
            .requestId(updateUserEmailAddressRequest.getRequestId())
            .token(jwtToken)
            .build();
        
        return updateUserEmailAddressResponse;
    }
}