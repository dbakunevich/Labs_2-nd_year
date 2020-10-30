#pragma once
#include "../graphics/visualizer.h"

class Elem {
private:
	int _data;
	int _index;
public:
    Elem(int data_, int index_)
	 : _data(data_), _index(index_) {}

    explicit operator int() const {
        return _data;
    }

    [[nodiscard]] int index() const{
        return _index;
    }
};
