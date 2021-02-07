package personal.bakunevich;

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
    public PrintStream getWriter() throws MyExceptions{
        if (writer == null)
            throw new MyExceptions("Can't open writing stream");
        return writer;
    }

    @Override
    public double peek() throws MyExceptions {
        if (stack.empty())
            throw new MyExceptions("Stack is empty");
        else
            return stack.peek();
    }

    @Override
    public double pop() throws MyExceptions {
        if (stack.empty())
            throw new MyExceptions("Stack is empty");
        else
            return stack.pop();
    }

    @Override
    public void push(double x) throws MyExceptions {
        if (Double.parseDouble(String.valueOf(x)) != x)
            throw new MyExceptions("Can't push to stack");
        stack.push(x);
    }

    @Override
    public double getDefine(String s) throws MyExceptions {
        Double tmp = defines.get(s);
        if (tmp == null){
            //log
            throw new MyExceptions("I haven't this define");
        }
        else
            return tmp;
    }

    @Override
    public void addDefine(String s, double x) throws MyExceptions {
        Double tmp = defines.put(s, x);
        if (tmp != null){
            throw new MyExceptions("I already have this defines, but I'll rewrite it for you)");
        }
    }
}
