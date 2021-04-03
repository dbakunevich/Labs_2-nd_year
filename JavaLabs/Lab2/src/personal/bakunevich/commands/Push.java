package personal.bakunevich.commands;

import personal.bakunevich.commandContext.ICommandContext;
import personal.bakunevich.exeptions.ArgsException;
import personal.bakunevich.exeptions.MyExceptions;

public class Push extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions {
        if (args.length == 0){
            throw new ArgsException("PUSH without arguments");
        }
        else if (args.length >= 2){
            throw new ArgsException("More them I need arguments");
        }
        else {
            var tmp = args[0].toString();
            if (isDouble(tmp)) {
                context.push(Double.parseDouble(tmp));
            } else {
                context.push(context.getDefine(tmp));
            }
        }
    }

    private boolean isDouble(String s){
        try {
            Double.parseDouble(s);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
