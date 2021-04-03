#pragma once
#include <string>
#include <SDL2/SDL.h>
#include "window.h"

struct Color {
    int r = 0;
    int g = 0;
    int b = 0;
    int a = 0;
    Color() = default;
    Color(int r_, int g_, int b_, int a_)
    : r(r_), g(g_), b(b_), a(a_) {}
    void set(int r_, int g_, int b_, int a_) {
        r = r_;
        g = g_;
        b = b_;
        a = a_;
    }
    void set(int i) {
        r = g = b = a = i;
    }
};

class Window {
public:
    Window();

    [[maybe_unused]] Window(std::string title_, int width_, int height_);
    [[nodiscard]] bool isClosed() const;
    void PoolEvents();
    void drawRect(const SDL_Rect *rect, const Color &color) const;
    void drawLine(int x1, int y1, int x2, int y2, const Color &color) const;
    void present() const;
    void clear() const;
    [[nodiscard]] int getWidth() const;
    [[nodiscard]] int getHeight() const;
    operator SDL_Renderer* () const;
    ~Window();
private:
    int init();

    SDL_Window *window = nullptr;
    SDL_Renderer *renderer = nullptr;
    bool is_closed = false;
    std::string title = "Sort";
    int width = 1200;
    int height = 900;
};