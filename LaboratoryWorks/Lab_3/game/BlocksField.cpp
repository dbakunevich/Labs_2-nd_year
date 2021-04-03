#include "BlocksField.hpp"

BlocksField::BlocksField(const sf::Vector2f & size, const sf::Vector2f & position, const sf::Color & color, int columns, int rows)
{
    sf::Vector2f blockSize(size.x / columns, size.y / rows);

    for (int i = 0; i < columns; i++)
    {
        for (int j = 0; j < rows; j++)
        {
            blocks.emplace_back(blockSize - sf::Vector2f(4.f, 4.f), position + sf::Vector2f(blockSize.x * i + 2.f, blockSize.y * j + 2.f), color);
        }
    }
    this -> max = columns * rows;
}

void BlocksField::Update(Ball & ball)
{
    if (blocks.remove_if([&ball](const Block & block)->bool { return ball.checkColission(block); })){
        count++;
        std::cout << "Your score: " << count << std::endl;
        if (count == this -> max)
            count = -1;
    }
}

void BlocksField::Draw(sf::RenderWindow & window)
{
    for (auto &block : blocks)
    {
       block.Draw(window);
    }
}
