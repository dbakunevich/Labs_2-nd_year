package personal.bakunevich;

import java.io.PrintWriter;

public interface ICommandContext {
    PrintWriter getWriter() throws MyExceptions;
    double peek() throws MyExceptions;
    double pop() throws MyExceptions;
    void push(double x) throws MyExceptions;
    double getDefine(String s) throws MyExceptions;
    void addDefine(String s, double x) throws MyExceptions;
}
