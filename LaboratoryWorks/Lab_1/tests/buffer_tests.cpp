#include "gtest/gtest.h"
#include "../main/circular_buffer.h"

//simple example
TEST(Test_1, DefaultConstructor) {
	EXPECT_NO_THROW(CircularBuffer<char>{});
}