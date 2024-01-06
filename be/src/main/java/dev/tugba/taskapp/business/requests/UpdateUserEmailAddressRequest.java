package dev.tugba.taskapp.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NonNull
@Builder
public class UpdateUserEmailAddressRequest {
    @NotNull(message = "email : must not be null")
    @NotEmpty(message = "email : must not be empty")
    @NotBlank(message = "email : must not be blank")
    private String email;

    @NotNull(message = "body : must not be null")
    @NotEmpty(message = "body : must not be empty")
    @NotBlank(message = "body : must not be blank")
    private String requestId;
}
