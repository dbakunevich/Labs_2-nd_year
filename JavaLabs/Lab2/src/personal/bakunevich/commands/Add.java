package personal.bakunevich.commands;

import personal.bakunevich.commandContext.ICommandContext;
import personal.bakunevich.exeptions.ArgsException;
import personal.bakunevich.exeptions.MyExceptions;

public class Add extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions {
        if (args.length >= 1)
            throw new ArgsException("I don't need args");
        double first = context.pop();
        double second = context.pop();
        context.push(first + second);
    }
}
