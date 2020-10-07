#include "circular_buffer.h"
#include <iostream>

using namespace std;

int main(int argc, char *argv[]) {
    const int a = 5;
    CircularBuffer<int> buff2(15);
    CircularBuffer<int> test(10, 5);
    CircularBuffer<int> check1 = test;
    for (int i = 0; i < 7; ++i) {
        buff2.push_back(i);
    }
    int b =  test.at(5);
    test.push_back(10);
    test.push_back(1);
    int c = test[0];
    int f = test[9];
    test.push_front(999);
    const CircularBuffer<int> test1(4, 'b');
    const char wqe = test1[2];
    const char assist = test1.at(2);
    test.front();
    test1.front();
    test.back();
    test1.back();
    cout << buff2.reserve();
    test.swap(buff2);
    test.insert(0, 0);

}