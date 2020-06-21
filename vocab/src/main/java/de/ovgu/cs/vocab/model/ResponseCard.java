package de.ovgu.cs.vocab.model;

/**
 * A card object which is send to users over the API.
 */
public class ResponseCard implements ICard{
    private String question;
    private String answer;
    private int level;
    private long id;

    public ResponseCard(String question, String answer, int level, long id) {
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public int getLevel() {
        return level;
    }
}
