package dev.tugba.taskapp.auth.config.concretes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import dev.tugba.taskapp.auth.config.abstracts.UserService;
import dev.tugba.taskapp.core.utilities.exceptions.UserNotFoundException;
import dev.tugba.taskapp.dataAccess.abstracts.UserRepository;
import dev.tugba.taskapp.helper.Helper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) {
                if(Helper.isValidEmail(username)){
                    return userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("no user found with this email"));
                }else {
                    return userRepository.findByTurkishId(username).orElseThrow(() -> new UserNotFoundException("no user found with this turkishId"));
                }
    
            }
            
        };
    }
}
