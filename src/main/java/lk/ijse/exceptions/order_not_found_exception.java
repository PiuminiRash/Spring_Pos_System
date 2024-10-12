package lk.ijse.exceptions;

public class order_not_found_exception extends RuntimeException{
    public order_not_found_exception() {
        super();
    }

    public order_not_found_exception(String message) {
        super(message);
    }

    public order_not_found_exception(String message, Throwable cause) {
        super(message, cause);
    }
}
