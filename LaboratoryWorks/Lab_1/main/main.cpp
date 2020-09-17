#include "circular_buffer.h"

using namespace std;

int main(int argc, char *argv[]) {
    CircularBuffer<int> buff2(10, 5);
    CircularBuffer<int> test(10, 6);

    buff2 = test;


    return 0;
}