package personal.bakunevich.commandContext;

import personal.bakunevich.exeptions.ContextExeptions;
import personal.bakunevich.exeptions.MyExceptions;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CommandContext implements ICommandContext {
    private final Stack<Double> stack = new Stack<>();
    private final Map<String, Double> defines = new HashMap<>();
    private final PrintStream writer;


    public CommandContext(PrintStream writer) {
        this.writer = writer;
    }

    @Override
    public PrintStream getWriter() throws MyExceptions {
        if (writer == null)
            throw new ContextExeptions("Can't open writing stream");
        return writer;
    }

    @Override
    public double peek() throws MyExceptions {
        if (stack.empty())
            throw new ContextExeptions("Stack is empty");
        else
            return stack.peek();
    }

    @Override
    public double pop() throws MyExceptions {
        if (stack.empty())
            throw new ContextExeptions("Stack is empty");
        else
            return stack.pop();
    }

    @Override
    public void push(double x) throws MyExceptions {
        if (Double.parseDouble(String.valueOf(x)) != x)
            throw new ContextExeptions("Can't push to stack");
        stack.push(x);
    }

    @Override
    public double getDefine(String s) throws MyExceptions {
        Double tmp = defines.get(s);
        if (tmp == null){
            //log
            throw new ContextExeptions("I haven't this define");
        }
        else
            return tmp;
    }

    @Override
    public void addDefine(String s, double x) throws MyExceptions {
        Double tmp = defines.put(s, x);
        if (tmp != null){
            throw new ContextExeptions("I already have this defines, but I'll rewrite it for you)");
        }
    }
}
