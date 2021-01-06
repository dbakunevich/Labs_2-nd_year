#include "Paddle.hpp"

Paddle::Paddle(const sf::Vector2f & size, const sf::Vector2f & position, const sf::Color & color, float speed)
{
    paddle.setSize(size);
    paddle.setPosition(position);
    paddle.setFillColor(color);
    paddle.setOrigin(size.x / 2.f, size.y / 2.f);

    this->speed = speed;
}

void Paddle::Update(float deltaTime)
{
    if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left) && paddle.getPosition().x > (0.f + (paddle.getSize().x / 2.f)))
        paddle.move(-speed * deltaTime, 0.f);
    if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right) && paddle.getPosition().x < (GlobalObjects::windowWidth - (paddle.getSize().x / 2.f)))
        paddle.move(speed * deltaTime, 0.f);
}

void Paddle::Draw(sf::RenderWindow & window)
{
    window.draw(paddle);
}
