package dev.tugba.taskapp.business.abstracts;

import dev.tugba.taskapp.business.requests.CreateTaskRequest;
import dev.tugba.taskapp.business.requests.DeleteTaskRequest;
import dev.tugba.taskapp.business.requests.UpdateTaskRequest;
import dev.tugba.taskapp.business.responses.DeleteTaskResponse;
import dev.tugba.taskapp.business.responses.GetAllTaskResponse;
import dev.tugba.taskapp.business.responses.PostTaskResponse;
import dev.tugba.taskapp.business.responses.UpdateTaskResponse;

public interface TaskService {
    GetAllTaskResponse getAllTask(String bearerToken, String requestId);
    PostTaskResponse add(CreateTaskRequest createTaskRequest, String bearerToken);
    DeleteTaskResponse delete(DeleteTaskRequest deleteTaskRequest);
    UpdateTaskResponse update(UpdateTaskRequest updateTaskRequest);
}
