package personal.bakunevich.factory;

import personal.bakunevich.commands.Command;
import personal.bakunevich.exeptions.MyExceptions;

public interface IFactory {
    Command getCommand(String command) throws MyExceptions;
}
