package dev.tugba.taskapp.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Data
@AllArgsConstructor
@NonNull
@Builder
public class UpdateTaskRequest {
    @NonNull
    private String taskId;

    @NonNull
    private String title;

    @NonNull
    private String body;
}
