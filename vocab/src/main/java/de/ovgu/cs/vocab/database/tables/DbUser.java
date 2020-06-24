package de.ovgu.cs.vocab.database.tables;

import javax.persistence.*;
import java.util.Set;

/**
 * A representation of a user in a database table.
 */
@Entity
public class DbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String apiKey;

    @Column(unique = true)
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<DbCard> cards;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String uername) {
        this.username = uername;
    }

    public Set<DbCard> getCards() {
        return cards;
    }

    public void setCards(Set<DbCard> cards) {
        this.cards = cards;
    }
}
