#include "circular_buffer.h"

int main(int argc, char *argv[]) {
    const int a = 5;
    CircularBuffer<int> buff2(15);
    CircularBuffer<int> test(10, a);

    buff2 = test;

    if (buff2 == test)
        return 1;

    return 0;
}