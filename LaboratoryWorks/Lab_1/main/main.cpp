#include "circular_buffer.h"

int main(int argc, char *argv[]) {
    const int a = 5;
    CircularBuffer<int> buff2(15);
    CircularBuffer<int> test(10, a);
    CircularBuffer<int> check1 = test;
    int b =  test.at(5);
}