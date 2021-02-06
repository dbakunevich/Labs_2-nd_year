package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;

public class Sqrt extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) {
        var first = context.pop();
        context.push(Math.sqrt(first));
    }
}
