#include <iostream>
#include <vector>
#include <cstdlib>
#include <set>

#include "../graphics/visualizer.h"
#include "element.h"

std::vector<Elem> create(int size) {
    std::srand(1000);
    std::vector<Elem> vect;
    std::set<int> used;
    int index = 0;
    while (vect.size() != size) {
        int val = std::rand() % size;
        if (!used.count(val)) {
            used.insert(val);
            vect.emplace_back(val, index);
            index++;
        }
    }
    Visualizer::getInstance().setContainer(vect.begin(), vect.end());
    Visualizer::getInstance().clear();
    return vect;
}

int main() {
    std::vector<Elem> vect{create(300)};
    Visualizer::getInstance().clear();

    std::sort(vect.begin(), vect.end());

    Visualizer::getInstance().draw();
    std::cout << "Поздравляем!!!!";
    return 0;
}