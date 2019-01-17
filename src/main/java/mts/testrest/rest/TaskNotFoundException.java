package mts.testrest.rest;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String id) {
        super("Could not find task " + id);
    }
}
