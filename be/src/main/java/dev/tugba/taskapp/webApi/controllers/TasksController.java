package dev.tugba.taskapp.webApi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.taskapp.business.abstracts.TaskService;
import dev.tugba.taskapp.business.requests.CreateTaskRequest;
import dev.tugba.taskapp.business.responses.GetAllTaskResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/task")
@AllArgsConstructor
public class TasksController {
    private TaskService taskService;

    @PostMapping
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<CreateTaskRequest> add(@RequestBody @Valid CreateTaskRequest createTaskRequest, @RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(this.taskService.add(createTaskRequest, bearerToken));
    }

    /* @GetMapping
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<String> delete(@PathVariable DeleteTaskRequest deleteTaskRequest) {
        this.taskService.delete(deleteTaskRequest);
        return ResponseEntity.ok("Success");
    } */
    
    @GetMapping
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<List<GetAllTaskResponse>> getAllTask(@RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(this.taskService.getAllTask(bearerToken));
    }
}
