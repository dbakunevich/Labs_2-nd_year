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
    }
};

class Compare :public Command{
private:
    int _value_1;
    int _value_2;
public:
    Compare(int first, int second) : _value_1(first), _value_2(second) {};
    virtual void execute(std::vector<int> &container, std::vector<Color> &colors) const{
        for(int i = 0; i < colors.size(); i++) {
            if (i == _value_1 || i == _value_2){
                colors[i].set(255, 0, 255, 2);
            }
        }
   }
};