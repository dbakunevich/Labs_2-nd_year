package personal.bakunevich.utils;

public class Time {

    public static final long SECOND = 1000000000L;

    public static long getSecond(){
        return System.nanoTime();
    }

}
