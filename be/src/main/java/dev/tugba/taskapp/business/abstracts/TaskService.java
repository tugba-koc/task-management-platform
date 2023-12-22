package dev.tugba.taskapp.business.abstracts;

import dev.tugba.taskapp.business.requests.CreateTaskRequest;
import dev.tugba.taskapp.business.requests.DeleteTaskRequest;

public interface TaskService {
    CreateTaskRequest add(CreateTaskRequest createTaskRequest);
    void delete(DeleteTaskRequest deleteTaskRequest);
}
