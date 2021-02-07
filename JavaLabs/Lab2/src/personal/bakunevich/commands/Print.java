package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;
import personal.bakunevich.MyExceptions;

import java.io.IOException;

public class Print extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions {
        if (args.length >= 1)
            throw new MyExceptions("I don't need args");
        try{
            write(context);
        } catch (IOException | MyExceptions e) {
            e.printStackTrace();
        }
    }

    private void write(ICommandContext context) throws IOException, MyExceptions {
        context.getWriter().print(String.format("%.3f\n", context.peek()));
        System.out.println("123");
    }
}
