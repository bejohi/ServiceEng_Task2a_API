package de.ovgu.cs.vocab.model;

/**
 * A generic response object which holds information about the success or failure of a operation and a related message.
 */
public class GenericResponse {
    private final boolean success;
    private final String message;

    public static GenericResponse ok(String message){
        return new GenericResponse(true,message);
    }

    public GenericResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
