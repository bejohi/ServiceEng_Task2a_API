package de.ovgu.cs.vocab.model;

/**
 * A vocabulary cards with getter and setters for the most important attributes.
 */
public interface ICard {
    long getId();
    void setId(long id);
    String getQuestion();
    void setQuestion(String question);
    String getAnswer();
    void setAnswer(String answer);
    int getLevel();
    void setLevel(int level);
}
