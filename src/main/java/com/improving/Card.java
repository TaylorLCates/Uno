package com.improving;

public class Card {

    private final Face face;
    private final Color color;
    boolean isAddressed = true;

    public Card(Face face, Color color) {
        this.face = face;
        this.color = color;
    }

    public Face getFace() {
        return face;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "" + color.toString() + " " + face.toString();
    }
}
