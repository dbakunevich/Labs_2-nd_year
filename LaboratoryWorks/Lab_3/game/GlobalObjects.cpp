#include "GlobalObjects.hpp"

const float GlobalObjects::windowWidth = 800.f;
const float GlobalObjects::windowHeight = 600.f;
sf::RenderWindow GlobalObjects::window(sf::VideoMode(windowWidth, windowHeight), "Arkanoid", sf::Style::Default);
