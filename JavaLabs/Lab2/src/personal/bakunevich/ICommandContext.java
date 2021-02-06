package personal.bakunevich;

public interface ICommandContext {
    double peek();
    double pop();
    void push(double x);
    double getDefine(String s);
    void addDefine(String s, double x);
}
