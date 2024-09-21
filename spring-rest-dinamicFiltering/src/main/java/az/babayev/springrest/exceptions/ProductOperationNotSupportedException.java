package az.babayev.springrest.exceptions;

public class ProductOperationNotSupportedException extends RuntimeException {
    public ProductOperationNotSupportedException(String message) {
        super(message);
    }
}
