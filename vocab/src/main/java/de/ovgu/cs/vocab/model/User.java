package de.ovgu.cs.vocab.model;

public class User implements IUser {

    private String username;
    private String apiKey;

    public User() {
    }

    public User(String username, String apiKey) {
        this.username = username;
        this.apiKey = apiKey;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
