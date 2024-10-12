package lk.ijse.exceptions;

public class data_persistent_exception extends RuntimeException{
    public data_persistent_exception() {
        super();
    }

    public data_persistent_exception(String message) {
        super(message);
    }

    public data_persistent_exception(String message, Throwable cause) {
        super(message, cause);
    }
}
