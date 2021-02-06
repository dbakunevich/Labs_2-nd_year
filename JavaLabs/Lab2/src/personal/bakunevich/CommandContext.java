package personal.bakunevich;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CommandContext implements ICommandContext {
    private final Stack<Double> stack = new Stack<>();
    private final Map<String, Double> defines = new HashMap<>();

    @Override
    public double peek() {
        return stack.peek();
    }

    @Override
    public double pop() {
        return stack.pop();
    }

    @Override
    public void push(double x) {
        stack.push(x);
    }

    @Override
    public double getDefine(String s) {
        Double tmp = defines.get(s);
//        if (tmp == null)
//            //log
//            throw NullPointerException ;
        return tmp;
    }

    @Override
    public void addDefine(String s, double x) {
        Double tmp = defines.put(s, x);
//        if (tmp != null){
//            throw Exception Log;daskjd lhao,h sofisduoircqw
//        }
    }
}
