package dev.tugba.taskapp.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAuthenticationRequest {
    @NotNull(message = "accountcode : must not be null")
    @NotEmpty(message = "accountcode : must not be empty")
    @NotBlank(message = "accountcode : must not be blank")
    private String accountcode;

    @NotNull(message = "password : must not be null")
    @NotEmpty(message = "password : must not be empty")
    @NotBlank(message = "password : must not be blank")
    private String password;
}
