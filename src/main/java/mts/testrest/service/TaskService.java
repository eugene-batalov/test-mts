package mts.testrest.service;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.transaction.Transactional;
import mts.testrest.model.Task;
import mts.testrest.persistance.TaskRepository;
import mts.testrest.rest.InvalidUuidException;
import mts.testrest.rest.TaskNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TaskService {

    private static final long TWO_MINUTES_IN_MILLIS = 120_000L;
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void setStatus(Task task, Task.Status status) {
        task.setStatus(status.toString().toLowerCase());
        task.setTimestamp(LocalDateTime.now());
        taskRepository.save(task);
    }

    @Async
    public void run(Task task) {
        setStatus(task, Task.Status.RUNNING);
        try {
            Thread.sleep(TWO_MINUTES_IN_MILLIS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        setStatus(task, Task.Status.FINISHED);
    }

    public Task getTask(@PathVariable String id) {
        validateUuid(id);
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Transactional
    public Task createTask() {
        String uuid = UUID.randomUUID().toString();
        Task task = Task.builder()
                .id(uuid)
                .status(Task.Status.CREATED.toString().toLowerCase())
                .timestamp(LocalDateTime.now())
                .build();
        taskRepository.save(task);
        return task;
    }

    private void validateUuid(String id) {
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidUuidException(id, e);
        }
    }
}
