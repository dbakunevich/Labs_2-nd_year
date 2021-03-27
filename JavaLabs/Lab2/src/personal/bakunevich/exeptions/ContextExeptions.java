package personal.bakunevich.exeptions;

public class ContextExeptions extends MyExceptions{

    public ContextExeptions() {
        System.err.println("Unknown error");
    }

    public ContextExeptions(String message) {
        super(message);
    }

    public ContextExeptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ContextExeptions(Throwable cause) {
        super(cause);
    }

    public ContextExeptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
