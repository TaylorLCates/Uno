package com.improving;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Deck deck = new Deck();
    int numOfPlayers;
    List<Player> listOfPlayers = new ArrayList<>();
    int currentTurn = 0;
    int turnDirection = 1;


    public Game(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public boolean isValidPlay(Card card) {
        if (card.getFace().equals(topCardOfDiscard().getFace())
                || card.getColor().equals(topCardOfDiscard().getColor())
                || card.getColor().equals(Color.WILD) || topCardOfDiscard().getColor().equals(Color.WILD)) {
            return true;
        } else return false;
    }

    public void reshuffle() {
        if (deck.getDrawPile().size() == 0) {
            for (int i = 0; i < deck.getDiscardPile().size(); i++) {
                deck.getDrawPile().add(deck.getDiscardPile().get(i));
                deck.getDiscardPile().remove(i);
                deck.getDrawPile().get(i).isAddressed = true;
            }
            System.out.println("Reshuffled " + deck.getDrawPile().size() + " " + deck.getDiscardPile().size());
        }
    }


    public void play() {
        boolean gameInProgress = true;
        for (int i = 0; i < numOfPlayers; i++) {
            listOfPlayers.add(new Player(new ArrayList<>(), i));
        }

        for (Player player : listOfPlayers) {
            player.draw(this);
            player.initializeHand();
        }

        if (currentTurn == 0) {
            deck.getDiscardPile().add(deck.getDrawPile().get(0));
            deck.getDrawPile().remove(deck.getDrawPile().get(0));
            System.out.println("The starting card is " + deck.getDiscardPile().get(0));
            if (isSpecialCard(deck.getDiscardPile().get(0))) {
                performSpecialCard(deck.getDiscardPile().get(0), this);
            }
        }

        while (gameInProgress) {

            if (currentTurn < 0) {
                currentTurn = currentTurn + listOfPlayers.size();
            }
            
            int activePlayerNumber = currentTurn % listOfPlayers.size();
            Player activeActualPlayer = listOfPlayers.get(activePlayerNumber);

            activeActualPlayer.takeTurn(this);

            if (isSpecialCard(topCardOfDiscard())) {
                performSpecialCard(topCardOfDiscard(), this);
            }

            if (activeActualPlayer.handSize() == 1) {
                System.out.println("Player " + activeActualPlayer.getId() + " shouts 'UNO!'");
            }
            if (activeActualPlayer.handSize() == 0) {
                System.out.println("Player " + activeActualPlayer.getId() + " has won the game!");
                gameInProgress = false;
                break;
            }
            currentTurn = currentTurn + turnDirection;
        }
    }

    public boolean isSpecialCard(Card card) {
        if (card.getFace().equals(Face.DRAW_FOUR) || card.getFace().equals(Face.DRAW_TWO)
                || card.getFace().equals(Face.REVERSE) || card.getFace().equals(Face.SKIP)) {
            return true;
        } else return false;
    }

    public void performSpecialCard(Card card, Game game) {

        int nextPlayer = (currentTurn + turnDirection) % listOfPlayers.size();
        int activePlayerNumber = currentTurn % listOfPlayers.size();


        if (card.getFace().equals(Face.DRAW_FOUR) && card.isAddressed) {
            listOfPlayers.get(nextPlayer).draw(game);
            listOfPlayers.get(nextPlayer).draw(game);
            listOfPlayers.get(nextPlayer).draw(game);
            listOfPlayers.get(nextPlayer).draw(game);
            card.isAddressed = false;
            currentTurn = currentTurn + turnDirection;

        }
        if (card.getFace().equals(Face.DRAW_TWO) && card.isAddressed) {
            listOfPlayers.get(nextPlayer).draw(game);
            listOfPlayers.get(nextPlayer).draw(game);
            card.isAddressed = false;
            currentTurn = currentTurn + turnDirection;
        }
        if (card.getFace().equals(Face.REVERSE) && card.isAddressed) {
            turnDirection = turnDirection *= -1;
            card.isAddressed = false;
            System.out.println("R-R-R-R-REVERSE!");
        }
        if (card.getFace().equals(Face.SKIP) && card.isAddressed) {
            card.isAddressed = false;
            currentTurn = currentTurn + turnDirection;
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public Card topCardOfDiscard() {
        return getDeck().getDiscardPile().get(getDeck().getDiscardPile().size() - 1);
    }

}
