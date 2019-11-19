package com.improving;

import java.util.List;
import java.util.Optional;

public interface IGame {

    void playCard(Card card, Optional<Colors> declaredColor);

    boolean isPlayable(Card card);

    Card draw();

    List<IPlayerInfo> getPlayerHandSizes();

    IPlayerInfo getNextPlayer();

    IPlayerInfo getPrevPlayer();

    IPlayerInfo getNextNextPlayer();

}
