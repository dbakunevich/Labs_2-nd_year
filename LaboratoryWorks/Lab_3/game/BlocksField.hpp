#pragma once

#include <list>
#include "Ball.hpp"

class BlocksField
{
private:
    std::list<Block> blocks;
    int max;

public:
    int count = 0;
    BlocksField(const sf::Vector2f & size, const sf::Vector2f & position, const sf::Color & color, int columns, int rows);

    void Update(Ball & ball);

    void Draw(sf::RenderWindow & window);
};

