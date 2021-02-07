package personal.bakunevich;

import java.io.IOException;

abstract public class Command {
    abstract public void execute(ICommandContext context, Object[] args) throws MyExceptions, IOException;
}
