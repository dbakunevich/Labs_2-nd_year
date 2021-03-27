package personal.bakunevich.commands;

import personal.bakunevich.commandContext.ICommandContext;
import personal.bakunevich.exeptions.ArgsException;
import personal.bakunevich.exeptions.MyExceptions;
import personal.bakunevich.exeptions.NumException;

public class Define extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions {
        if (args.length >= 3)
            throw new ArgsException("More them I need arguments");
        else if (args.length <= 1)
            throw new ArgsException("Less them I need arguments");
        else if (isDouble(args[0].toString())){
            throw new NumException("I can't define something to double" +
                                    "!!! DOUBLE !-> SOMETHING");
        }
        else context.addDefine(args[0].toString(), Double.parseDouble(args[1].toString()));
    }

    public boolean isDouble(String s){
        try {
            Double.parseDouble(s);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
