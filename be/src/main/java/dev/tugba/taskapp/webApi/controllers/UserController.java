package dev.tugba.taskapp.webApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.tugba.taskapp.business.abstracts.UserRequestService;
import dev.tugba.taskapp.business.requests.UpdateUserEmailAddressRequest;
import dev.tugba.taskapp.business.responses.GetAllUserDataResponse;
import dev.tugba.taskapp.business.responses.UpdateUserEmailAddressResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('VISITOR')")
@AllArgsConstructor
public class UserController {
    private UserRequestService userRequestService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('visitor:read')")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<GetAllUserDataResponse> getAllUserData(@RequestHeader("Authorization") String bearerToken, @RequestParam String requestId) {
        return ResponseEntity.ok(this.userRequestService.getAllUserData(bearerToken, requestId));
    }

    @PatchMapping("/update")
    @PreAuthorize("hasAuthority('visitor:update')")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<UpdateUserEmailAddressResponse> updateuserEmailAddress(@RequestHeader("Authorization") String bearerToken, @RequestBody @Valid UpdateUserEmailAddressRequest updateUserEmailAddressRequest) {
        return ResponseEntity.ok(this.userRequestService.updateUserEmailAddress(bearerToken, updateUserEmailAddressRequest));
    }
}
