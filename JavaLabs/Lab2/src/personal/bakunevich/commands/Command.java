package personal.bakunevich.commands;

import personal.bakunevich.commandContext.ICommandContext;
import personal.bakunevich.exeptions.MyExceptions;

import java.io.IOException;

abstract public class Command {
    abstract public void execute(ICommandContext context, Object[] args) throws MyExceptions, IOException;
}
