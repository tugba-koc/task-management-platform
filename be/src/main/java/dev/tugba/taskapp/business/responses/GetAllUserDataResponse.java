package dev.tugba.taskapp.business.responses;

import java.io.Serializable;
import java.util.List;

import dev.tugba.taskapp.entities.concretes.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserDataResponse implements Serializable {
    private String firstname;
    private String lastname;
    private String accountcode;
    private List<Task> tasks;
}