package com.improving;

public interface IPlayer {

    int handSize();

    Card draw(IGame game);

    void takeTurn(IGame game);



}
