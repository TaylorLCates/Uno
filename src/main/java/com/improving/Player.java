package com.improving;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Player implements PlayerInterface{

    private Random random = new Random();

    private List<Card> playerHand;

    private Game game;
    private int id;


    Player(ArrayList<Card> playerHand, int id) {
        this.playerHand = playerHand;
        this.id = id;
    }


    public void takeTurn(Game game) {
        boolean cardPlayed = false;
        for (Card card : playerHand) {
            if (game.isValidPlay(card)) {
                play(card);
                System.out.println("Player " + id + " played " + card);
                cardPlayed = true;
                //game.currentTurn = game.currentTurn + game.turnDirection;
                break;
            }
        }
        if (!cardPlayed) {
            draw(game);
            //game.currentTurn = game.currentTurn + game.turnDirection;
            if (game.isValidPlay(playerHand.get(playerHand.size() - 1))) {
                play(playerHand.get(playerHand.size() - 1));
                System.out.println("Player " + id + " played card that was drawn");
            }
        }
    }


    private void play(Card card) {
        game.getDeck().getDiscardPile().add(card);
        playerHand.remove(card);
    }

    public Card draw(Game game) {
        this.game = game;
        game.reshuffle();
        var randomIndex = random.nextInt(game.getDeck().getDrawPile().size());
        var card = game.getDeck().getDrawPile().get(randomIndex);
        game.getDeck().getDrawPile().remove(randomIndex);
        playerHand.add(card);
        System.out.println("Player " + id + " drew " + card);
        game.reshuffle();
        return card;
    }

    void initializeHand() {
        for (int i = 0; i < 6; i++) {
            draw(game);
        }
    }


    public int handSize() {
        return playerHand.size();
    }

    int getId() {
        return id;
    }

}




