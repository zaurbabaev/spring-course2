package az.babayev.springrest.exceptions;


public class LanguageNotFoundException extends RuntimeException {
    public LanguageNotFoundException(String message) {
        super(message);
    }
}
