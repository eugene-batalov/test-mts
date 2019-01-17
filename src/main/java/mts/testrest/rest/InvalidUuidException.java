package mts.testrest.rest;

public class InvalidUuidException extends RuntimeException {
    public InvalidUuidException(String id, Throwable cause) {
        super("Invalid UUID " + id, cause);
    }
}
