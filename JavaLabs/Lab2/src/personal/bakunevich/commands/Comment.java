package personal.bakunevich.commands;

import personal.bakunevich.commandContext.ICommandContext;
import personal.bakunevich.exeptions.MyExceptions;

import java.io.IOException;

public class Comment extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) {
        try{
            write(context, args);
        } catch (IOException | MyExceptions e) {
            e.printStackTrace();
        }
    }


    private void write(ICommandContext context, Object[] args) throws IOException, MyExceptions {
        context.getWriter().print("#");
        for (var auto: args){
            context.getWriter().printf(" %s", auto);
        }
        context.getWriter().print("\n");
    }
}
