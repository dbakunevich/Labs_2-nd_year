package personal.bakunevich.commands;

import personal.bakunevich.commandContext.ICommandContext;
import personal.bakunevich.exeptions.ArgsException;
import personal.bakunevich.exeptions.MyExceptions;
import personal.bakunevich.exeptions.NumException;

public class Div extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions {
        if (args.length >= 1)
            throw new ArgsException("I don't need args");
        double first = context.pop();
        double second = context.pop();
        if (second == 0)
            throw new NumException("Division by zero");
        context.push(first / second);
    }
}
