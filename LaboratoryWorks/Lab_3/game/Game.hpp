#pragma once

#include <SFML/Graphics.hpp>
#include "BlocksField.hpp"

class Game
{
private:
    static Ball * ball;
    static Paddle * paddle;
    static Score * score;
    Game() = delete;

public:
    static void createBall(const Ball & bll) { ball = new Ball(bll); }
    static void createPaddle(const Paddle & padd) { paddle = new Paddle(padd); }
    static void createScore(const Score & scr) { score = new Score(scr); }
    static bool Update(float deltaTime, BlocksField & blocksField);
    static void Draw(sf::RenderWindow & window);
};

