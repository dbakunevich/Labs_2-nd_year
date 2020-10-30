#pragma once
#include "../graphics/visualizer.h"

using namespace std;

class Elem {
private:
	int _data;
	int _index;
public:
    Elem(int data_, int index_)
	 : _data(data_), _index(index_) {}

	 ~Elem() = default;

    explicit operator int() const {
        return _data;
    }

    [[nodiscard]] int index() const{
        return _index;
    }

    Elem &operator=(const Elem &assist) {
        cout << "Присваивание значению индекса: " << (*this)._index;
        cout << " значение индекса: " << assist._index << endl;
        Visualizer::getInstance().pushCommand(new Assign(assist.operator int(), index()));
        if (this == &assist) {
            return (*this);
        }
        (*this)._data = assist._data;
        return *this;
    }
};

bool operator<(const Elem &assist_1, const Elem &assist_2) {
    cout << "Сравнение значения индекса: " << assist_1.index();
    cout << " со значением индекса: " << assist_2.index() << endl;
    Visualizer::getInstance().pushCommand(new Compare(assist_1.index(), assist_2.index()));
    return assist_1.operator int() < assist_2.operator int();
}