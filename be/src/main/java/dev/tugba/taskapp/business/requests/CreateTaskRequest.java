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
public class CreateTaskRequest {
    @NotNull(message = "title : must not be null")
    @NotEmpty(message = "title : must not be empty")
    @NotBlank(message = "title : must not be blank")
    private String title;

    @NotNull(message = "body : must not be null")
    @NotEmpty(message = "body : must not be empty")
    @NotBlank(message = "body : must not be blank")
    private String body;

    @NotNull(message = "body : must not be null")
    @NotEmpty(message = "body : must not be empty")
    @NotBlank(message = "body : must not be blank")
    private String requestId;
}
