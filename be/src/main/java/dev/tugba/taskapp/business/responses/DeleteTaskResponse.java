package dev.tugba.taskapp.business.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTaskResponse {
    private String status;
    private String requestId;
    private LocalDateTime datetime;
}
