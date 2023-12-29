package dev.tugba.taskapp.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tugba.taskapp.auth.config.abstracts.JwtService;
import dev.tugba.taskapp.business.abstracts.TaskService;
import dev.tugba.taskapp.business.requests.CreateTaskRequest;
import dev.tugba.taskapp.business.requests.DeleteTaskRequest;
import dev.tugba.taskapp.business.responses.GetAllTaskResponse;
import dev.tugba.taskapp.core.utilities.mappers.ModelMapperService;
import dev.tugba.taskapp.dataAccess.abstracts.TaskRepository;
import dev.tugba.taskapp.dataAccess.abstracts.UserRepository;
import dev.tugba.taskapp.entities.concretes.Task;
import dev.tugba.taskapp.entities.concretes.User;
import dev.tugba.taskapp.helper.Helper;

@Service
public class TaskManager implements TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ModelMapperService modelMapperService;

    public TaskManager(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void delete(DeleteTaskRequest deleteTaskRequest) {
        Task task = this.taskRepository.findById(deleteTaskRequest.getTaskId());
        this.taskRepository.delete(task);
         // TODO: ad an exception
         /* .orElseThrow(() -> new ReviewNotFoundException(ErrorCodeConstants.REVIEW_NOT_FOUND.getErrorCode())); */
    }

    @Override
    public List<GetAllTaskResponse> getAllTask(String bearerToken) {
        String token = Helper.extractToken(bearerToken);
        int userId = this.jwtService.extractUserId(token);

        User user = this.userRepository.findById(userId).orElseThrow();

        List<Task> tasks = this.taskRepository.findAllByUserId(user.getId());

        
        List<GetAllTaskResponse> taskList = tasks.stream()
                .map(task->this.modelMapperService.forResponse()
                        .map(task,GetAllTaskResponse.class)).collect(Collectors.toList());

        return taskList;
    }

    @Override
    public CreateTaskRequest add(CreateTaskRequest createTaskRequest, String bearerToken) {
        String token = Helper.extractToken(bearerToken);
        int userId = this.jwtService.extractUserId(token);

        User user = this.userRepository.findById(userId).orElseThrow();
        
        Task task = this.modelMapperService.forRequest().map(createTaskRequest,Task.class);
        task.setUser(user);
        this.taskRepository.save(task);
        return createTaskRequest;
    }
}
