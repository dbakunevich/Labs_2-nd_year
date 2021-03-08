package personal.bakunevich.exeptions;

public class ArgsException extends MyExceptions{
    public ArgsException() {
        System.err.println("Unknown error");
    }

    public ArgsException(String message) {
        super(message);
    }

    public ArgsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgsException(Throwable cause) {
        super(cause);
    }

    public ArgsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
