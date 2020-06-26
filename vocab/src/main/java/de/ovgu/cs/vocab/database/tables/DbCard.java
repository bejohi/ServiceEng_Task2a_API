package de.ovgu.cs.vocab.database.tables;

import javax.persistence.*;

/**
 * A DB table representation of a vocabulary card.
 */
@Entity
public class DbCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition="TEXT")
    private String question;

    @Column(columnDefinition="TEXT")
    private String answer;

    @ManyToOne
    private DbUser user;

    private int level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public DbUser getUser() {
        return user;
    }

    public void setUser(DbUser user) {
        this.user = user;
    }
}
