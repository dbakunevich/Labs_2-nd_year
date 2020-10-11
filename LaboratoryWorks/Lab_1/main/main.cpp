#include "circular_buffer.h"
#include <iostream>
#include <gtest/gtest.h>

using namespace std;

TEST(T, T) {
    CircularBuffer<int> buff2(10);
    CircularBuffer<int> buff1(10);
    for (int i = 0; i < buff2.capacity() + 5; i++) {
        buff2.push_back(i + 1);
        for (int j = 0; j < buff2.capacity(); j++) {
            cout << buff2[j] << ' ';
        }
        cout << endl;
    }
}

int main(int argc, char *argv[]) {
    CircularBuffer<int> buff2(4);
    buff2.push_back(11);
    /*for (auto i = 0; i < buff2.capacity(); i++) {
        cout << buff2[i] << ' ';
    }
    cout << endl;*/
    buff2.push_front(-11);
    /*for (auto i = 0; i < buff2.capacity(); i++) {
        cout << buff2[i] << ' ';
    }
    cout << endl;*/
    buff2.pop_back();
    /*for (auto i = 0; i < buff2.capacity(); i++) {
        cout << buff2[i] << ' ';
    }
    cout << endl;*/
    cout << "back" << buff2.back() << endl << "front" << buff2.front();
    /*CircularBuffer<int> buff1(10);
    for (int i = 0; i < buff2.capacity() + 3; i++) {
        buff2.push_back(i + 1);
        for (int j = 0; j < buff2.capacity(); j++) {
            cout << buff2[j] << ' ';
        }
        cout << endl;
    }
    buff2.resize(10, -1);
    for (auto i = 0; i < buff2.capacity(); i++) {
        cout << buff2[i] << ' ';
    }
    cout << endl;
    buff2.pop_front();
    for (auto i = 0; i < buff2.capacity(); i++) {
        cout << buff2[i] << ' ';
    }*/
}