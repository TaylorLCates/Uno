package com.improving;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Deck deck = new Deck();
    int numOfPlayers;
    List<Player> listOfPlayers = new ArrayList<>();
    int currentTurn;


    public Game(int numOfPlayers){
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
            }
            System.out.println("Reshuffled " + deck.getDrawPile().size() + " " + deck.getDiscardPile().size());
        }
    }



    public void play(){
        boolean gameInProgress = true;
        for (int i = 0; i < numOfPlayers; i++) {
            listOfPlayers.add(new Player(new ArrayList<>(), i));
            System.out.println(listOfPlayers.toString());
        }
        for (Player player : listOfPlayers){
            player.draw(this);
                player.initializeHand();
            }
        int turncounter = 0;
        if (turncounter == 0) {
        deck.getDiscardPile().add(deck.getDrawPile().get(0));
        deck.getDrawPile().remove(deck.getDrawPile().get(0));
            System.out.println("The starting card is " + deck.getDiscardPile().get(0));}
        turncounter++;


        while (gameInProgress) {
            for (Player player : listOfPlayers) {
                player.takeTurn(this);
                currentTurn = player.getId();
                if (player.getPlayerHandSize() == 1) {
                    System.out.println("Player " + player.getId() + " shouts 'UNO!'");
                }
                if (player.getPlayerHandSize() == 0) {

                    System.out.println("Player " + player.getId() + " has won the game!");
                    gameInProgress = false;
                    break;
                }
            }
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public Card topCardOfDiscard() {
        return getDeck().getDiscardPile().get(getDeck().getDiscardPile().size() - 1);
    }

    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void doINeedToDraw() {

    }
}
