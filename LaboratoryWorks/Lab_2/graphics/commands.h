#include <vector> 
#include "window.h"

class Command {
public:
    virtual ~Command() = default;
    virtual void execute(std::vector<int> &container, std::vector<Color> &colors) const = 0;
};

class Assign : public Command{
private:
    int _value_1;
    int _value_2;
public:
    Assign(int first, int second) : _value_1(first), _value_2(second){};
    virtual void execute(std::vector<int> &container, std::vector<Color> &colors) const {
        container[_value_1] = _value_2;
        colors[_value_1].set(0, 255, 255, 0);
        colors[_value_2].set(0, 255, 255, 0);
    }
};

class Compare :public Command{
private:
    int _value_1;
    int _value_2;
public:
    Compare(int first, int second) : _value_1(first), _value_2(second) {};
    virtual void execute(std::vector<int> &container, std::vector<Color> &colors) const{
        colors[_value_1].set(255, 0, 255, 0);
        colors[_value_2].set(255, 0, 255, 0);
   }
};