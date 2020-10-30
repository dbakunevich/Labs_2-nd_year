#include <iostream>

#include "visualizer.h"


void Visualizer::pushCommand(Command *command) {
    command_list.emplace_back(command);
}

Visualizer::Visualizer() = default;

Visualizer& Visualizer::getInstance() {
    static Visualizer inctance;
    return inctance;
}

void Visualizer::draw() {
    Window window;
    if (elements.empty()) {
        std::cerr << "Add container first!" << std::endl;
        return;
    }
    int max_elem = * std::max_element(elements.begin(), elements.end());
    float texture_width = static_cast<float>(window.getWidth()) / elements.size();
    float texture_height = static_cast<float>(window.getHeight()) / static_cast<float>(max_elem);

    size_t previousTime = 0;
    [[maybe_unused]] size_t currentTime = 0;
    SDL_Event e;
    while (true) {
        currentTime = SDL_GetTicks();
        if (currentTime - previousTime > 10 || previousTime == 0) {
            window.clear();
            previousTime = currentTime;
            if (!command_list.empty()) {
               command_list.front()->execute(elements, colors);
               command_list.pop_front();
            }
            for (int i = 0; i < elements.size(); i++) {
                SDL_Rect rect;
                rect.w = static_cast<int>(texture_width);
                rect.h = static_cast<int>(texture_height) * elements[i];
                rect.x = static_cast<int>(texture_width) * i;
                rect.y = window.getHeight() - rect.h;
                window.drawRect(&rect, colors[i]);
                window.drawLine(rect.x, rect.y, rect.x, rect.y + rect.h, Color{});
            }
            window.present();
            for (auto& color : colors) 
                color.set(125);
        }
        if (window.isClosed() || (e.key.keysym.sym == SDLK_DELETE)){
            break;
        }
        window.PoolEvents();

    } 
}

void Visualizer::clear() {
    command_list.clear();
}
