package com.improving;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Game implements IGame {
    Deck deck = new Deck();
    int numOfPlayers;
    List<Player> listOfPlayers = new ArrayList<>();
    int currentTurn = 0;
    int turnCount=0;
    int turnDirection = 1;
    Optional<Colors> currentColor = null;


    public Game(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public boolean isPlayable(Card card) {
        if (card.getColors().equals(Colors.WILD)) {
            return true;
        }
        if (topCardOfDiscard().getColors().equals(Colors.WILD) && card.getColors().equals(currentColor.get())) {
            return true;
        }
        if (!(topCardOfDiscard().getColors().equals(Colors.WILD)) && card.getFaces().equals(topCardOfDiscard().getFaces())
                    || card.getColors().equals(topCardOfDiscard().getColors()) || topCardOfDiscard().getColors().equals(Colors.WILD)) {
                return true;
            }
        else return false;
    }

    @Override
    public Card draw() {
        reshuffle();
        Random random = new Random();
        var randomIndex = random.nextInt(getDeck().getDrawPile().size());
        var card = getDeck().getDrawPile().get(randomIndex);
        getDeck().getDrawPile().remove(randomIndex);
        //System.out.println("Player " + listOfPlayers.get(currentTurn % listOfPlayers.size()).getId() + " drew " + card);
        reshuffle();
        return card;
    }

    @Override
    public List<IPlayerInfo> getPlayerHandSizes() {
        ArrayList<IPlayerInfo> playerHandSizes = new ArrayList<>();
        for (IPlayer player : listOfPlayers) {
            playerHandSizes.add((IPlayerInfo)player);
        }
        return null;
    }

    @Override
    public IPlayerInfo getNextPlayer() {
        return (IPlayerInfo) listOfPlayers.get((currentTurn % listOfPlayers.size()) + turnDirection);
    }

    @Override
    public IPlayerInfo getPrevPlayer() {
        IPlayerInfo prevPlayer = (IPlayerInfo) listOfPlayers.get((currentTurn % listOfPlayers.size()) - turnDirection);
        return prevPlayer;
    }

    @Override
    public IPlayerInfo getNextNextPlayer() {
        IPlayerInfo nextNextPlayer = (IPlayerInfo) listOfPlayers.get(((currentTurn % listOfPlayers.size()) + turnDirection) + turnDirection);
        return nextNextPlayer;
    }


    public ArrayList<IPlayerInfo> getPlayerInfo(){
        ArrayList<IPlayerInfo> playerInfo = new ArrayList<>();
        for (IPlayer player: listOfPlayers) {
            playerInfo.add((IPlayerInfo) player);
        }
        return playerInfo;
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

    public void playCard(Card card, Optional<Colors> declaredColor) {
       getDeck().getDiscardPile().add(card);
       if (declaredColor.isPresent()) {
           currentColor = declaredColor;
       } else currentColor = null;
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

        if (turnCount == 0) {
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
            currentTurn += turnDirection;
            ++turnCount;

            if (activeActualPlayer.handSize() == 1) {
                //System.out.println("Player " + activeActualPlayer.getId() + " shouts 'UNO!'");
            }
            if (activeActualPlayer.handSize() == 0) {
                System.out.println("Player " + activeActualPlayer.getId() + " has won the game!");
                gameInProgress = false;
                break;
            }
        }
    }

    public boolean isSpecialCard(Card card) {
        if (card.getFaces().equals(Faces.DRAW_FOUR) || card.getFaces().equals(Faces.DRAW_TWO)
                || card.getFaces().equals(Faces.REVERSE) || card.getFaces().equals(Faces.SKIP)) {
            return true;
        } else return false;
    }

    public void performSpecialCard(Card card, Game game) {
        if (currentTurn < 6) {
            currentTurn = currentTurn + listOfPlayers.size();
        }
        int nextPlayer = (currentTurn + turnDirection) % listOfPlayers.size();
        int activePlayerNumber = currentTurn % listOfPlayers.size();

        if (card.getFaces().equals(Faces.DRAW_FOUR) && card.isAddressed) {
            if (turnCount == 0) {
                listOfPlayers.get(nextPlayer - turnDirection).draw(game);
                listOfPlayers.get(nextPlayer - turnDirection).draw(game);
                listOfPlayers.get(nextPlayer - turnDirection).draw(game);
                listOfPlayers.get(nextPlayer - turnDirection).draw(game);
                card.isAddressed = false;
                currentColor = Optional.of(Colors.RED);
                currentTurn += turnDirection;
            }
            listOfPlayers.get(nextPlayer).draw(game);
            listOfPlayers.get(nextPlayer).draw(game);
            listOfPlayers.get(nextPlayer).draw(game);
            listOfPlayers.get(nextPlayer).draw(game);
            card.isAddressed = false;
            currentTurn += turnDirection;

        }
        if (card.getFaces().equals(Faces.DRAW_TWO) && card.isAddressed) {
            if (turnCount == 0) {
                listOfPlayers.get(nextPlayer - turnDirection).draw(game);
                listOfPlayers.get(nextPlayer - turnDirection).draw(game);
                card.isAddressed = false;
                currentTurn += turnDirection;
            }
            listOfPlayers.get(nextPlayer).draw(game);
            listOfPlayers.get(nextPlayer).draw(game);
            card.isAddressed = false;
            currentTurn += turnDirection;
        }
        if (card.getFaces().equals(Faces.REVERSE) && card.isAddressed) {
            turnDirection = turnDirection *= -1;
            card.isAddressed = false;
            System.out.println("R-R-R-R-REVERSE!");
        }
        if (card.getFaces().equals(Faces.SKIP) && card.isAddressed) {
            card.isAddressed = false;
            currentTurn += turnDirection;
        }
        if (card.getFaces().equals(Faces.WILD) && (turnCount == 0)){
            currentColor = Optional.of(Colors.RED);
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
}
