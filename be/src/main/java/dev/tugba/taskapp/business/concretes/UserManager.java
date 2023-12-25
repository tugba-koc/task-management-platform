package dev.tugba.taskapp.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tugba.taskapp.auth.config.concretes.JwtService;
import dev.tugba.taskapp.business.abstracts.UserService;
import dev.tugba.taskapp.business.responses.GetAllUserDataResponse;
import dev.tugba.taskapp.core.utilities.mappers.ModelMapperService;
import dev.tugba.taskapp.dataAccess.abstracts.UserRepository;
import dev.tugba.taskapp.entities.concretes.User;

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
    public void getAllUserData(String bearerToken) {
        String username = this.jwtService.extractUsername(bearerToken);
        User user = this.userRepository.findByTurkishId(username);

        GetAllUserDataResponse userdata = new GetAllUserDataResponse();
        userdata.setFirstname(user.getFirstname());
        userdata.setLastname(user.getLastname());
    }
}