package com.improving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Deck {
    private final List<Card> drawPile = new ArrayList<>();
    private final List<Card> discardPile = new ArrayList<>();
    private final Random random = new Random();

    public Deck(){
        Set<Faces> wildFaces = new HashSet<>();
        wildFaces.add(Faces.WILD);
        wildFaces.add(Faces.DRAW_FOUR);

        for (var color : Colors.values()) {
            for (var face : Faces.values()) {
                if (color != Colors.WILD){
                if (wildFaces.contains(face)) {
                    System.out.print("");
                } else for (int i = 0; i < 2; i++) {
                    drawPile.add(new Card(face, color));
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            drawPile.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            drawPile.add(new Card(Faces.WILD, Colors.WILD));
        }
        Collections.shuffle(drawPile);
        //System.out.println(cards.toString());
    }



    public List<Card> getDrawPile() {
        return drawPile;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

}
