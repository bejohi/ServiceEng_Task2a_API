package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.model.IUser;

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
}
