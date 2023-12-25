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
public class GetAllUserDataRequest {
    @NotNull(message = "requestId : must not be null")
    @NotEmpty(message = "requestId : must not be empty")
    @NotBlank(message = "requestId : must not be blank")
    private String requestId;
}