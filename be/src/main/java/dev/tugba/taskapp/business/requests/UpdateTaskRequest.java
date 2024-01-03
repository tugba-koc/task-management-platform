package dev.tugba.taskapp.business.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Data
@AllArgsConstructor
@NonNull
@Builder
public class UpdateTaskRequest {
    @NotNull(message = "taskId : must not be null")
    private int taskId;

    private String title;

    private String body;
}
