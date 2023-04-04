package exception;

public class NoSuchEventTypeException extends RuntimeException {

    public NoSuchEventTypeException(String message) {
        super(message);
    }
}
