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
    CircularBuffer<int> buff2(10);
    CircularBuffer<int> buff1(10);
    for (int i = 0; i < buff2.capacity() + 5; i++) {
        buff2.push_back(i + 1);
        for (int j = 0; j < buff2.capacity(); j++) {
            cout << buff2[j] << ' ';
        }
        cout << endl;
    }
    //buff2.linearize();
    buff2.pop_front();
    for (auto i = 0; i < buff2.capacity(); i++) {
        cout << buff2[i] << ' ';
    }
}