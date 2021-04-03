package personal.bakunevich.commands;

import personal.bakunevich.commandContext.ICommandContext;
import personal.bakunevich.exeptions.MyExceptions;

import java.io.IOException;

public class Comment extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws IOException, MyExceptions{
        write(context, args);
    }


    private void write(ICommandContext context, Object[] args) throws MyExceptions {
        context.getWriter().print("#");
        for (var auto: args){
            context.getWriter().printf(" %s", auto);
        }
        context.getWriter().print("\n");
    }
}
