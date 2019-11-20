package com.improving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SmartPlayer {

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

        HashMap<Colors, int[]> m=new HashMap<>();
        m.put(Colors.RED, new int[]{0});
        m.put(Colors.GREEN, new int[]{0});
        m.put(Colors.YELLOW, new int[]{0});
        m.put(Colors.BLUE, new int[]{0});
        m.put(Colors.WILD, new int[]{0});
        for (Card card : playerHand) {
            if (card.getColors().equals(Colors.BLUE)) {
                m.get(Colors.BLUE) [0]++;
            }
            if (card.getColors().equals(Colors.RED)) {
                m.get(Colors.RED)[0]++;
            }
            if (card.getColors().equals(Colors.GREEN)) {
                m.get(Colors.GREEN)[0]++;
            }
            if (card.getColors().equals(Colors.YELLOW)) {
                m.get(Colors.YELLOW)[0]++;
            }
            if (card.getColors().equals(Colors.WILD)) {
                m.get(Colors.WILD)[0]++;
            }
        }

        return
    }



        int getId() {
            return id;
        }

    }

}
