package personal.bakunevich;

public interface IFactory {
    Command getCommand(String command) throws MyExceptions;
}
