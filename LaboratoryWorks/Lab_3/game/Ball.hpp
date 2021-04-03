#pragma once

#include <SFML/Graphics.hpp>
#include <iostream>
#include "Block.hpp"
#include "GlobalObjects.hpp"
#include "Paddle.hpp"
#include "Score.hpp"

class Ball
{
private:
    sf::CircleShape circle;
    int count;
    float speed;
    sf::Vector2f velocity;

    void setAngle(float ang);
    float getAngle() const;

public:
    Ball(float radius, const sf::Vector2f & position, const sf::Color & color, float speed, float angle);

    bool checkColission(const Block & block);
    bool checkColission(const Paddle & paddle);
    bool exist() { return bottom() < GlobalObjects::windowHeight; }

    void Update(float deltaTime);

    void Draw(sf::RenderWindow & window);

    float left()   const { return circle.getPosition().x - circle.getRadius(); }
    float rigth()  const { return circle.getPosition().x + circle.getRadius(); }
    float top()    const { return circle.getPosition().y - circle.getRadius(); }
    float bottom() const { return circle.getPosition().y + circle.getRadius(); }

    float getX() const { return circle.getPosition().x; }
    float getY() const { return circle.getPosition().y; }
    sf::Vector2f getPosition() const { return circle.getPosition(); }
    float getRadius() const { return circle.getRadius(); }
};