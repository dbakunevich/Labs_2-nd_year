#include "Block.hpp"

Block::Block(const sf::Vector2f & size, const sf::Vector2f & position, const sf::Color & color)
{
    rect.setSize(size);
    rect.setPosition(position);
    rect.setFillColor(color);
}

void Block::Draw(sf::RenderWindow & window)
{
    window.draw(rect);
}
