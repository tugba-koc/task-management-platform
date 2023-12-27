package dev.tugba.taskapp.webApi.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.tugba.taskapp.business.abstracts.UserRequestService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private UserRequestService userRequestService;

    @GetMapping
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public void getAllUserDataResponse(@RequestHeader("Authorization") String bearerToken) {
        this.userRequestService.getAllUserData(bearerToken);
    }
}
