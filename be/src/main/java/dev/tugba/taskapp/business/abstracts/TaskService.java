package dev.tugba.taskapp.business.abstracts;

import java.util.List;

import dev.tugba.taskapp.business.requests.CreateTaskRequest;
import dev.tugba.taskapp.business.requests.DeleteTaskRequest;
import dev.tugba.taskapp.business.responses.GetAllTaskResponse;

public interface TaskService {
    List<GetAllTaskResponse> getAllTask(String bearerToken);
    CreateTaskRequest add(CreateTaskRequest createTaskRequest, String bearerToken);
    void delete(DeleteTaskRequest deleteTaskRequest);
}
