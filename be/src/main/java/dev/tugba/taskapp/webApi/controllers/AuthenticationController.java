package dev.tugba.taskapp.webApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.taskapp.business.abstracts.AuthenticationService;
import dev.tugba.taskapp.business.requests.CreateAuthenticationRequest;
import dev.tugba.taskapp.business.requests.CreateRegisterRequest;
import dev.tugba.taskapp.business.responses.GetAuthenticationResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<GetAuthenticationResponse> register(@RequestBody CreateRegisterRequest createRegisterRequest) {
        return ResponseEntity.ok(this.authenticationService.register(createRegisterRequest));
    }

    @PostMapping("/authenticate")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<GetAuthenticationResponse> authenticate(@RequestBody CreateAuthenticationRequest createAuthenticationRequest) {
        return ResponseEntity.ok(this.authenticationService.authenticate(createAuthenticationRequest));
    }
}
