package personal.bakunevich.commands;

import personal.bakunevich.commandContext.ICommandContext;
import personal.bakunevich.exeptions.ArgsException;
import personal.bakunevich.exeptions.MyExceptions;
import personal.bakunevich.exeptions.NumException;

public class Sqrt extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions {
        if (args.length >= 1)
            throw new ArgsException("I don't need args");
        var first = context.pop();

        if (first < 0){
           context.push(first);
           throw new NumException("Number less than zero");
        }
        else {
            context.push(Math.sqrt(first));
        }
    }
}
