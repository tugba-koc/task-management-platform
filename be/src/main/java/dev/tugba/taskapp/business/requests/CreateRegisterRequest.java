package dev.tugba.taskapp.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRegisterRequest {
    private String firstName;

    private String lastName;

    private String turkishId;

    private String email;

    private String password;
}
