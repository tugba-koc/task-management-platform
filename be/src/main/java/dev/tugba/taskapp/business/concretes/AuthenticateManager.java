package dev.tugba.taskapp.business.concretes;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.tugba.taskapp.auth.config.concretes.JwtService;
import dev.tugba.taskapp.auth.config.constants.Role;
import dev.tugba.taskapp.business.abstracts.AuthenticationService;
import dev.tugba.taskapp.business.requests.CreateAuthenticationRequest;
import dev.tugba.taskapp.business.requests.CreateRegisterRequest;
import dev.tugba.taskapp.business.responses.GetAuthenticationResponse;
import dev.tugba.taskapp.dataAccess.abstracts.UserRepository;
import dev.tugba.taskapp.entities.concretes.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticateManager implements AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional(readOnly = false)
    public GetAuthenticationResponse register(CreateRegisterRequest createRegisterRequest) {
        User user = User.builder()
        .firstname(createRegisterRequest.getFirstname())
        .lastname(createRegisterRequest.getLastname())
        .email(createRegisterRequest.getEmail())
        .turkishId(createRegisterRequest.getTurkishId())
        .password(this.passwordEncoder.encode(createRegisterRequest.getPassword()))
        .role(Role.USER)
        .build();

        // add user to userRepository
        this.userRepository.save(user);

        // create new token for the user
        String jwtToken = this.jwtService.getToken(user);

        // return token in response
        return GetAuthenticationResponse.builder()
            .token(jwtToken).build();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "AuthenticationService::findByEmail", key = "#createAuthenticationRequest.email")
    public GetAuthenticationResponse authenticate(CreateAuthenticationRequest createAuthenticationRequest) {
        User user;
    
        try {
            // Try to authenticate the user
            this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    createAuthenticationRequest.getAccountcode(), createAuthenticationRequest.getPassword()));
    
            // If authentication is successful, proceed to find the user
            if (createAuthenticationRequest.getAccountcode() != null) {
                if (createAuthenticationRequest.getAccountcode().contains("@")) {
                    user = this.userRepository.findByEmail(createAuthenticationRequest.getAccountcode());
                } else {
                    user = this.userRepository.findByTurkishId(createAuthenticationRequest.getAccountcode());
                }
            } else {
                // TODO: add an exception
                return null;
            }
    
            String jwtToken = this.jwtService.getToken(user);
            return GetAuthenticationResponse.builder()
                .token(jwtToken).build();
        } catch (Exception e) {
            // TODO: add an exception
            return null;
        }
        
    }
    

    // TODO: add logout operation
}
