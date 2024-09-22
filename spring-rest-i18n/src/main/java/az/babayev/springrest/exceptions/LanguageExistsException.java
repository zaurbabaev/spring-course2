package az.babayev.springrest.exceptions;

public class LanguageExistsException extends RuntimeException {

    public LanguageExistsException(String message) {
        super(message);
    }
}
