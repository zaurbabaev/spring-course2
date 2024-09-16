package az.babayev.springrestpart2.exceptions;

public class ProductOperationNotSupportedException extends RuntimeException {
    public ProductOperationNotSupportedException(String message) {
        super(message);
    }
}
