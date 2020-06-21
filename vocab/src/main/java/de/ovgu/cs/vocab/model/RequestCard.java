package de.ovgu.cs.vocab.model;

/**
 * A card object which can be send by the user in order to store it in the database. Can be used for put and post
 * requests.
 */
public class RequestCard {
    private String question;
    private String answer;
    private int level;
    private long id;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
