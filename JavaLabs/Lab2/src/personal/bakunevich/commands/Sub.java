package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;

public class Sub extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) {
        var first = context.pop();
        var second = context.pop();
        context.push(first - second);
    }
}
