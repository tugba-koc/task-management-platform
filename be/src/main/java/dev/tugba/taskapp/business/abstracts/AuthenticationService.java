package dev.tugba.taskapp.business.abstracts;

import dev.tugba.taskapp.business.requests.CreateAuthenticationRequest;
import dev.tugba.taskapp.business.requests.CreateRegisterRequest;
import dev.tugba.taskapp.business.responses.GetAuthenticationResponse;

public interface AuthenticationService {
    GetAuthenticationResponse register(CreateRegisterRequest createRegisterRequest);
    GetAuthenticationResponse authenticate(CreateAuthenticationRequest createAuthenticationRequest);
}
