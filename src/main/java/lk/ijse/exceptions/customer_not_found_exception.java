package lk.ijse.exceptions;

public class customer_not_found_exception extends RuntimeException{
    public customer_not_found_exception() {
        super();
    }

    public customer_not_found_exception(String message) {
        super(message);
    }

    public customer_not_found_exception(String message, Throwable cause) {
        super(message, cause);
    }
}
