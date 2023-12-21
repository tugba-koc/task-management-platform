package dev.tugba.taskapp.business.concretes;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.tugba.taskapp.auth.config.concretes.JwtManager;
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
    private final JwtManager jwtManager;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional(readOnly = false)
    public GetAuthenticationResponse register(CreateRegisterRequest createRegisterRequest) {
        User user = User.builder()
        .firstName(createRegisterRequest.getFirstName())
        .lastName(createRegisterRequest.getLastName())
        .email(createRegisterRequest.getEmail())
        .turkishId(createRegisterRequest.getTurkishId())
        .password(this.passwordEncoder.encode(createRegisterRequest.getPassword()))
        .role(Role.USER)
        .build();

        // add user to userRepository
        this.userRepository.save(user);

        // create new token for the user
        String jwtToken = this.jwtManager.generateToken(user);

        // return token in response
        return GetAuthenticationResponse.builder()
            .token(jwtToken).build();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "AuthenticationService::findByEmail", key = "#createAuthenticationRequest.email")
    public GetAuthenticationResponse authenticate(CreateAuthenticationRequest createAuthenticationRequest) {
        System.out.println("createAuthenticationRequest  >>> " + createAuthenticationRequest);
        User user;
            if (createAuthenticationRequest.getEmail() != null) {
                this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    createAuthenticationRequest.getEmail(), createAuthenticationRequest.getPassword()));
                    user = this.userRepository.findByEmail(createAuthenticationRequest.getEmail());
            } else {
                System.out.println("testtest2");
                this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    createAuthenticationRequest.getTurkishId(), createAuthenticationRequest.getPassword()));
                    user = this.userRepository.findByTurkishId(createAuthenticationRequest.getTurkishId());
            }
        
        
        String jwtToken = this.jwtManager.generateToken(user);
        return GetAuthenticationResponse.builder()
            .token(jwtToken).build();
    }

    // TODO: add logout operation
}
