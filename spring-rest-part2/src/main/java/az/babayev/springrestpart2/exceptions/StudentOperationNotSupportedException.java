package az.babayev.springrestpart2.exceptions;

public class StudentOperationNotSupportedException extends RuntimeException {

    public StudentOperationNotSupportedException(String message) {
        super(message);
    }
}
