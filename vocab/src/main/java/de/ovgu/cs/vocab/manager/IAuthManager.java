package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.model.IUser;

import java.util.Optional;

/**
 * Provides methods to authenticate users and add new users (with API-keys) to the system.
 * Also manages the API keys in general.
 */
public interface IAuthManager {
    /**
     * Checks whether the given API-key is registered in the system or not. In case it isn't an unauthorized HTTP
     * exception is thrown, otherwise the related user is returned.
     * @param apiKey the apiKey, send by the user.
     * @return the user related to the ApiKey, in case one is found.
     */
    IUser authenticate(String apiKey);

    /**
     * Register's a new user in the system.
     * @param userName the name of the new user to register in the system.
     * @return Optional with the new API-key in case of a success or an empty Optional otherwise.
     */
    Optional<String> addUser(String userName);
}
