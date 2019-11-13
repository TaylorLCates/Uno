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
        Set<Face> wildFaces = new HashSet<>();
        wildFaces.add(Face.WILD);
        wildFaces.add(Face.DRAW_FOUR);

        for (var color : Color.values()) {
            for (var face : Face.values()) {
                if (color != Color.WILD){
                if (wildFaces.contains(face)) {
                    System.out.print("");
                } else for (int i = 0; i < 2; i++) {
                    drawPile.add(new Card(face, color));
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            drawPile.add(new Card(Face.DRAW_FOUR,Color.WILD));
            drawPile.add(new Card(Face.WILD, Color.WILD));
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
