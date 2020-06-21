package de.ovgu.cs.vocab.model;

/**
 * A vocabulary cards with getter for the most important attributes.
 */
public interface ICard {

    /**
     * @return a unique id of the card.
     */
    long getId();

    /**
     * @return the question of the card (frontside of the card).
     */
    String getQuestion();

    /**
     * @return the answer of the card (backside of the card).
     */
    String getAnswer();

    /**
     * @return the current level of the card.
     */
    int getLevel();
}
