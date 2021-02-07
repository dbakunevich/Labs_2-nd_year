package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;
import personal.bakunevich.MyExceptions;

public class Push extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) throws MyExceptions {
        if (args.length == 0){
            throw new MyExceptions("PUSH without arguments");
        }
        else if (args.length >= 2){
            throw new MyExceptions("More them I need arguments");
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
