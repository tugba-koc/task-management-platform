package dev.tugba.taskapp.business.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteTaskRequest {
    @NotNull(message = "taskId : must not be null")
    private int taskId;
}
