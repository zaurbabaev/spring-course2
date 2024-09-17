package az.babayev.springrestpart2.exceptions;

public class MyDataAlreadyExistsException extends RuntimeException {
    public MyDataAlreadyExistsException(String message) {
        super(message);
    }
}
