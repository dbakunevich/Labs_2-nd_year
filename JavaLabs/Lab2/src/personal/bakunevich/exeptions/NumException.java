package personal.bakunevich.exeptions;

public class NumException extends MyExceptions{
    public NumException() {
        System.err.println("Unknown error");
    }

    public NumException(String message) {
        super(message);
    }

    public NumException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumException(Throwable cause) {
        super(cause);
    }

    public NumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
