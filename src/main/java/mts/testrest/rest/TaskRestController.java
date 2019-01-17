package mts.testrest.rest;

import java.util.UUID;
import mts.testrest.model.Task;
import mts.testrest.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    public ResponseEntity postTask() {
        Task task = taskService.createTask();
        taskService.run(task);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(task.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity greeting(@PathVariable String id) {
        Task task = taskService.getTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
}
