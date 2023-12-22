package dev.tugba.taskapp.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tugba.taskapp.business.abstracts.TaskService;
import dev.tugba.taskapp.business.requests.CreateTaskRequest;
import dev.tugba.taskapp.business.requests.DeleteTaskRequest;
import dev.tugba.taskapp.core.utilities.mappers.ModelMapperService;
import dev.tugba.taskapp.dataAccess.abstracts.TaskRepository;
import dev.tugba.taskapp.entities.concretes.Task;

@Service
public class TaskManager implements TaskService {
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    public TaskManager(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public CreateTaskRequest add(CreateTaskRequest createTaskRequest) {
        // TODO: creating BusinessRules to handle errors
        /* this.brandBusinessRules.checkIfBrandNameAlreadyExists(createBrandRequest.getName()); */
        Task task = this.modelMapperService.forRequest().map(createTaskRequest,Task.class);
        Task insertedTask = this.taskRepository.save(task);
        if(insertedTask == null) {
            // TODO: add an exception
            return null;
        }
        return createTaskRequest;
    }

    @Override
    public void delete(DeleteTaskRequest deleteTaskRequest) {
        Task task = this.taskRepository.findById(deleteTaskRequest.getTaskId());
        this.taskRepository.delete(task);
         // TODO: ad an exception
         /* .orElseThrow(() -> new ReviewNotFoundException(ErrorCodeConstants.REVIEW_NOT_FOUND.getErrorCode())); */
    }
}
