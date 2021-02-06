package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;

public class Push extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) {
//        var tmp = args[0].toString();
//        if (tmp == null){
//
//        }
//        else if (tmp.)
        context.push((Double) args[0]);
    }
}
