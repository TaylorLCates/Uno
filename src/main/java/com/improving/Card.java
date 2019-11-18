package com.improving;

public class Card {

    private final Faces faces;
    private final Colors colors;
    boolean isAddressed = true;

    public Card(Faces faces, Colors colors) {
        this.faces = faces;
        this.colors = colors;
    }

    public Faces getFaces() {
        return faces;
    }

    public Colors getColors() {
        return colors;
    }

    public boolean isAddressed() {
        return isAddressed;
    }

    public void setAddressed(boolean addressed) {
        isAddressed = addressed;
    }

    @Override
    public String toString() {
        return "" + colors.toString() + " " + faces.toString();
    }
}
