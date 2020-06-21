package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.model.ResponseCard;
import de.ovgu.cs.vocab.model.User;

import java.util.List;

/**
 * Provides methods to work with Vocabulary Cards
 */
public interface IVocManager {
    List<ResponseCard> getAllCardsForUser(User user);
}
