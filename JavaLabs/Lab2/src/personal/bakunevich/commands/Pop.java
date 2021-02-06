package personal.bakunevich.commands;

import personal.bakunevich.Command;
import personal.bakunevich.ICommandContext;

public class Pop extends Command {
    @Override
    public void execute(ICommandContext context, Object[] args) {
        context.pop();
    }
}
