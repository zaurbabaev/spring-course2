package az.babayev.springrest.exceptions;

public class StudentOperationNotSupportedException extends RuntimeException {

    public StudentOperationNotSupportedException(String message) {
        super(message);
    }
}
