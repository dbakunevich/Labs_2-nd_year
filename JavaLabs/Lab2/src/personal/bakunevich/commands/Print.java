package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;
import personal.bakunevich.MyExceptions;

import java.io.IOException;

public class Print extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions, IOException {
        if (args.length >= 1)
            throw new MyExceptions("I don't need args");

        write(context);
    }

    private void write(ICommandContext context) throws MyExceptions {
        context.getWriter().printf("%.3f\n", context.peek());
    }
}
