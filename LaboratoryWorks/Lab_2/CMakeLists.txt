cmake_minimum_required(VERSION 3.17)
project(Lab_2)

set(CMAKE_CXX_STANDARD 20)

find_package(SDL2 REQUIRED SDL2)

add_executable(lab_2 main/main.cpp main/element.h graphics/commands.h graphics/visualizer.h graphics/visualizer.cpp graphics/window.cpp graphics/window.h)

target_link_libraries(lab_2 PRIVATE SDL2)