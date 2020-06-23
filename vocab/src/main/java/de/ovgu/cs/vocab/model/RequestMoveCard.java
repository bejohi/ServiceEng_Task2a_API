package de.ovgu.cs.vocab.model;

/**
 * Holds all necessary properties in order to move a card between different levels.
 */
public class RequestMoveCard {
    private long id;
    private int newLevel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(int newLevel) {
        this.newLevel = newLevel;
    }
}
