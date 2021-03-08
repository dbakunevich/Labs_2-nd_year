package personal.bakunevich.commandContext;

import personal.bakunevich.exeptions.MyExceptions;

import java.io.PrintStream;

public interface ICommandContext {
    PrintStream getWriter() throws MyExceptions;
    double peek() throws MyExceptions;
    double pop() throws MyExceptions;
    void push(double x) throws MyExceptions;
    double getDefine(String s) throws MyExceptions;
    void addDefine(String s, double x) throws MyExceptions;

}
