package dev.tugba.taskapp.business.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRegisterRequest {
    @NotNull(message = "firstname : must not be null")
    @NotEmpty(message = "firstname : must not be empty")
    @NotBlank(message = "firstname : must not be blank")
    private String firstname;

    @NotNull(message = "lastname : must not be null")
    @NotEmpty(message = "lastname : must not be empty")
    @NotBlank(message = "lastname : must not be blank")
    private String lastname;

    @NotNull(message = "turkishId : must not be null")
    @NotEmpty(message = "turkishId : must not be empty")
    @NotBlank(message = "turkishId : must not be blank")
    @Size(min = 11, max = 11, message = "turkishId : must be exactly 11")
    @Pattern(regexp = "^\\d*\\.?\\d+$", message = "turkishId : Geçerli bir pozitif sayı giriniz.")
    private String turkishId;

    @NotNull(message = "email : must not be null")
    @NotEmpty(message = "email : must not be empty")
    @NotBlank(message = "email : must not be blank")
    @Email(message = "email : must be correct email format")
    private String email;

    @NotNull(message = "password : must not be null")
    @NotEmpty(message = "password : must not be empty")
    @NotBlank(message = "password : must not be blank")
    private String password;
}
