package de.ovgu.cs.vocab.model;

public class User implements IUser {

    private String username;

    @Override
    public String getUsername() {
        return this.username;
    }
}
