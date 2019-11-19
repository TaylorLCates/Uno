package com.improving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Player implements IPlayer {

    private Random random = new Random();
    private List<Card> playerHand;
    private IGame game;
    private int id;
    Colors declaredColors;


   public Player(ArrayList<Card> playerHand, int id) {
        this.playerHand = playerHand;
        this.id = id;
    }


    public void takeTurn(IGame game) {
        boolean cardPlayed = false;


        for (Card card : playerHand) {
            if (game.isPlayable(card)) {
                if (card.getColors().equals(Colors.WILD)) {
                    if (playerHand.size() == 0) {
                        declaredColors = Colors.RED;
                    } else
                        declaredColors = playerHand.get(0).getColors();
                    System.out.println("The color is now " + declaredColors);
                }
                game.playCard(card, Optional.ofNullable(declaredColors));
                System.out.println("Player " + id + " played " + card);
                cardPlayed = true;
                playerHand.remove(card);
                break;
            }
        }

        if (!cardPlayed) {
            draw(game);
            var drawnCard = playerHand.get(playerHand.size() - 1);
            if (game.isPlayable(drawnCard)) {
                if (drawnCard.getColors().equals(Colors.WILD)) {
                    if (playerHand.size() == 0) {
                        declaredColors = Colors.RED;
                    } else
                        declaredColors = playerHand.get(0).getColors();
                    System.out.println("The color is now " + declaredColors);
                    game.playCard(drawnCard, Optional.ofNullable(declaredColors));
                    System.out.println("Player " + id + " played card that was drawn");
                }
            }
        }
    }


    public Card draw(IGame game) {
        this.game = game;
        var card = game.draw();
        playerHand.add(card);
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

    Colors findBestColor() {

        int blueCounter = 0;
        int redCounter = 0;
        int greenCounter = 0;
        int yellowCounter = 0;
        for (Card card : playerHand) {
            if (card.getColors().equals(Colors.BLUE)) {
                blueCounter++;
            }
            if (card.getColors().equals(Colors.RED)) {
                redCounter++;
            }
            if (card.getColors().equals(Colors.GREEN)) {
                greenCounter++;
            }
            if (card.getColors().equals(Colors.YELLOW)) {
                yellowCounter++;
            }
        }

        Integer.compare(blueCounter, redCounter)



        return
    }



    int getId() {
        return id;
    }

}




