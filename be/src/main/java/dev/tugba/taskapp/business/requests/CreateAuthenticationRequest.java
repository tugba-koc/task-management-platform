package dev.tugba.taskapp.business.requests;

import jakarta.validation.constraints.AssertTrue;
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

    @AssertTrue(message = "accountcode: must be a valid email or have 11 digit characters")
    private boolean isValidTurkishId() {
        return accountcode.matches("^\\d*\\.?\\d+$") || isValidEmail(accountcode);
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$");
    }

    @NotNull(message = "password : must not be null")
    @NotEmpty(message = "password : must not be empty")
    @NotBlank(message = "password : must not be blank")
    private String password;

    @NotNull(message = "password : must not be null")
    @NotEmpty(message = "password : must not be empty")
    @NotBlank(message = "password : must not be blank")
    private String requestId;
}
