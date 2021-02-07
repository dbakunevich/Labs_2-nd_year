package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;
import personal.bakunevich.MyExceptions;

public class Mul extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions {
        if (args.length >= 1)
            throw new MyExceptions("I don't need args");
        double first = context.pop();
        double second = context.pop();
        context.push(first * second);
    }
}
