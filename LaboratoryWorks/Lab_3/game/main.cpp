#include <SFML/Graphics.hpp>
#include "GlobalObjects.hpp"
#include "BlocksField.hpp"
#include "Paddle.hpp"
#include "Game.hpp"
#include <iostream>

int main()
{
    srand(time(nullptr));

    sf::Clock clock;
    float deltaTime;
    GlobalObjects::window.setVerticalSyncEnabled(true);

    BlocksField blocksField(sf::Vector2f(GlobalObjects::windowWidth - 20.f, 200.f), sf::Vector2f(10.f, 25.f), sf::Color::Yellow, rand() % 5 + 5, rand() % 5 + 5);
    Game::createBall(Ball(10.f, sf::Vector2f((GlobalObjects::windowWidth / 2.f), (GlobalObjects::windowHeight / 2.f)), sf::Color::Red, 300.f, 270.f));
    Game::createPaddle(Paddle(sf::Vector2f(100.f, 10.f), sf::Vector2f((GlobalObjects::windowWidth / 2.f), GlobalObjects::windowHeight - 5), sf::Color::Green, 500.f));
    //Game::createScore(Score(sf::Vector2f(300.f, 400.f)));

    while (GlobalObjects::window.isOpen())
    {
        sf::Event event{};
        deltaTime = clock.restart().asSeconds();

        while (GlobalObjects::window.pollEvent(event))
        {
            if  (event.type == sf::Event::Closed ||
                (event.type == sf::Event::KeyPressed && event.key.code == sf::Keyboard::Escape))
            {
                GlobalObjects::window.close();
            }
        }


        if (!Game::Update(deltaTime, blocksField)) {
            std::cout << "You lose!";
            break;
        }
        else if(blocksField.count == -1){
            std::cout << "You win!";
            break;
        }

        GlobalObjects::window.clear(sf::Color::Black);

        blocksField.Draw(GlobalObjects::window);
        Game::Draw(GlobalObjects::window);

        GlobalObjects::window.display();
    }

    return 0;
}
