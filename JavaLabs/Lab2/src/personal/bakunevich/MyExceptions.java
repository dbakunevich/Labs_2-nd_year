package personal.bakunevich;

public class MyExceptions {
    private final Object NullPointerException = null;

    public MyExceptions() throws Throwable {
        assert false;
        throw (Throwable) NullPointerException;
    }
}
