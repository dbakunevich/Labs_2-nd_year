#include "circular_buffer.h"
#include <iostream>

using namespace std;

int main(int argc, char *argv[]) {
    CircularBuffer<int> buff2(10);
    CircularBuffer<int> buff1(10);
    for (int i = 0; i < buff2.capacity() - 5; i++) {
        buff2.push_back(i + 1);
        for (int j = 0; j < buff2.capacity(); j++) {
            cout << buff2[j] << ' ';
        }
        cout << endl;
    }
    buff2.pop_back();
    for (int i = 0; i < buff2.capacity(); i++) {
        cout << buff2[i] << ' ';
    }




}