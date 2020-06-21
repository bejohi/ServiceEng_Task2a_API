package de.ovgu.cs.vocab.database;

import de.ovgu.cs.vocab.model.IUser;

/**
 * Provides (direct or indirect) access to the user-database
 */
public interface IUserRepo {
    /**
     * @param apiKey the apikey of the user to find.
     * @return the matching user if one is found for the API-key, null otherwise.
     */
    IUser getUser(String apiKey);
}
