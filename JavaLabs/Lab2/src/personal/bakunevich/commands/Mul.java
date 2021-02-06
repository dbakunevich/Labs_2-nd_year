package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;

public class Mul extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) {
        double first = context.pop();
        double second = context.pop();
        context.push(first * second);
    }
}
