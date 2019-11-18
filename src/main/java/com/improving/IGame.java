package com.improving;

import java.util.List;
import java.util.Optional;

public interface IGame {

    void playCard(Card card, Optional<Colors> declaredColor);

    boolean isPlayable(Card card);

    Card draw();

    List<Integer> getPlayerHandSizes();

    IPlayer getNextPlayer();

    IPlayer getPrevPlayer();

    IPlayer getNextNextPlayer();



}
