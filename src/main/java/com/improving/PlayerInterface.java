package com.improving;

public interface PlayerInterface {

    int handSize();

    Card draw(Game game);

    void takeTurn(Game game);

}
