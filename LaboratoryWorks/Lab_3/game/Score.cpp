#include "Score.hpp"

Score::Score(const sf::Vector2f &position)
{
    font.loadFromFile("/home/dmitry/CLionProjects/OOP/ooop-19208/LaboratoryWorks/Lab_3/game/Impact Regular.ttf");
    text.setFont(font);
    text.setCharacterSize(100);
    text.setFillColor(sf::Color::White);
    text.setPosition(position);
    text.setStyle(sf::Text::Bold | sf::Text::Underlined);
    text.setString("qwe");
}

void Score::Draw(sf::RenderWindow & window)
{
    window.draw(text);
}
