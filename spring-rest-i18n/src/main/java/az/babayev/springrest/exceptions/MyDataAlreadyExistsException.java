package az.babayev.springrest.exceptions;

public class MyDataAlreadyExistsException extends RuntimeException {
    public MyDataAlreadyExistsException(String message) {
        super(message);
    }
}
