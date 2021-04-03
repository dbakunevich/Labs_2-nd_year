#include "Game.hpp"
#include <iostream>

Ball * Game::ball = nullptr;
Paddle * Game::paddle = nullptr;
Score * Game::score = nullptr;

bool Game::Update(float deltaTime, BlocksField & blocksField)
{
    if (paddle != nullptr)
        paddle->Update(deltaTime);

    if (ball != nullptr && ball->exist())
    {
        ball->Update(deltaTime);
        ball->checkColission(*paddle);
        blocksField.Update(*ball);
        return true;
    }

    else
    {
        delete ball;
        ball = nullptr;
        return false;
    }
}

void Game::Draw(sf::RenderWindow & window)
{
    if (ball != nullptr)
        ball->Draw(window);
    if (paddle != nullptr)
        paddle->Draw(window);
    if (score != nullptr)
        score->Draw(window);
}
