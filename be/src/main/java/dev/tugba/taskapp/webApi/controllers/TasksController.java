package dev.tugba.taskapp.webApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.taskapp.business.abstracts.TaskService;
import dev.tugba.taskapp.business.requests.CreateTaskRequest;
import dev.tugba.taskapp.business.requests.DeleteTaskRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/task")
@AllArgsConstructor
public class TasksController {
    private TaskService taskService;

    @PostMapping
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<CreateTaskRequest> add(@RequestBody CreateTaskRequest createTaskRequest) {
        return ResponseEntity.ok(this.taskService.add(createTaskRequest));
    }

    @DeleteMapping("/{taskId}")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<String> delete(@PathVariable DeleteTaskRequest deleteTaskRequest) {
        this.taskService.delete(deleteTaskRequest);
        return ResponseEntity.ok("Success");
    }
    
}
