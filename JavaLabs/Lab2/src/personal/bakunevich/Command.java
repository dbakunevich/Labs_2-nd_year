package personal.bakunevich;

abstract public class Command {
    abstract public void execute(ICommandContext context, Object[] args);
}
