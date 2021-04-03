cmake_minimum_required(VERSION 3.17)
project(Lab_4)

set(CMAKE_CXX_STANDARD 17)

include_directories(CSV_Parser)

add_executable(Lab_4
        CSV_Parser/csv_parser.h
        main.cpp CSV_Parser/tuple.h CSV_Parser/iter.h)
