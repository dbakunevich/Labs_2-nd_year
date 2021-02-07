package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;
import personal.bakunevich.MyExceptions;

public class Define extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions {
        if (args.length >= 3)
            throw new MyExceptions("More them I need arguments");
        else if (args.length <= 1)
            throw new MyExceptions("Less them I need arguments");
        else context.addDefine(args[0].toString(), Double.parseDouble(args[1].toString()));
    }
}
