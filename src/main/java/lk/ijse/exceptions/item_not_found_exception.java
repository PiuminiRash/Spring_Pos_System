package lk.ijse.exceptions;

public class item_not_found_exception extends RuntimeException{
    public item_not_found_exception() {
        super();
    }

    public item_not_found_exception(String message) {
        super(message);
    }

    public item_not_found_exception(String message, Throwable cause) {
        super(message, cause);
    }
}
