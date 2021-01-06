#pragma once

#include <SFML/Graphics.hpp>
#include "GlobalObjects.hpp"
#include "Ball.hpp"

class Score
{
private:
    sf::Text text;
    sf::Font font;
    int score = 0;

public:
    Score(const sf::Vector2f & position);

    void Draw(sf::RenderWindow & window);
};