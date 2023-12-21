package dev.tugba.taskapp.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAuthenticationRequest {
    private String email;

    private String turkishId;

    private String password;
}
