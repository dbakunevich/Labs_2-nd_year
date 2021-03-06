#include "circular_buffer.h"
#include <iostream>
#include <gtest/gtest.h>

using namespace std;

TEST(T, T) {
    CircularBuffer<int> buff2(11);
    for (int i = 0; i < buff2.capacity(); ++i) {
        if (i < 5)
             buff2.push_back(i);
        else if (i > 6)
             buff2.push_front(i);
    }
    CircularBuffer<int> test1 = buff2;
    for (auto i = 0; i < buff2.capacity(); i++) {
        EXPECT_EQ(test1[i / 2], buff2[i]);
    }
    test1.resize(12, 0);
    ASSERT_EQ(test1.capacity(), buff2.capacity());
//    cout << endl;
//    buff2.erase(3, 7);
//    for (auto i = 0; i < buff2.capacity(); i++) {
//        cout << buff2[i] << ' ';
//    }
//    cout << endl;
//    /*buff2.push_front(-11);
//    for (auto i = 0; i < buff2.capacity(); i++) {
//        cout << buff2[i] << ' ';
//    }
//    cout << endl;*/
//    buff2.rotate(5);
//    for (auto i = 0; i < buff2.capacity(); i++) {
//        cout << buff2[i] << ' ';
//    }
//    cout << endl;
//    buff2.rotate(1);
//    for (auto i = 0; i < buff2.capacity(); i++) {
//        cout << buff2[i] << ' ';
//    }
//    cout << endl;
//    buff2.insert(0, 100000);
//    for (auto i = 0; i < buff2.capacity(); i++) {
//        cout << buff2[i] << ' ';
//    }
//    cout << endl;
//    buff2.rotate(5);
//    for (auto i = 0; i < buff2.capacity(); i++) {
//        cout << buff2[i] << ' ';
//    }
//    cout << endl;
//    CircularBuffer<int> buff1(10);
//    for (int i = 0; i < buff2.capacity() + 3; i++) {
//        buff2.push_back(i + 1);
//        for (int j = 0; j < buff2.capacity(); j++) {
//            cout << buff2[j] << ' ';
//        }
//        cout << endl;
//    }
//    buff2.resize(10, -1);
//    for (auto i = 0; i < buff2.capacity(); i++) {
//        cout << buff2[i] << ' ';
//    }
//    cout << endl;
//    buff2.pop_front();
//    for (auto i = 0; i < buff2.capacity(); i++) {
//        cout << buff2[i] << ' ';
//    }
//    cout << endl;
}

int main(int argc, char *argv[]) {
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
