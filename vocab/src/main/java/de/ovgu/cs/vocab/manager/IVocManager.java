package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.model.IUser;
import de.ovgu.cs.vocab.model.RequestCard;
import de.ovgu.cs.vocab.model.ResponseCard;

import java.util.List;

/**
 * Provides methods to work with Vocabulary Cards
 */
public interface IVocManager {
    /**
     * @param user the owner of some cards.
     * @return A list of all cards which are associated with the given user.
     */
    List<ResponseCard> getAllCardsForUser(IUser user);

    /**
     * @param user the owner of cards.
     * @return a randomly selected card for a user and a dummy card in case no real card is available for this user.
     */
    ResponseCard getNextCardForUser(IUser user);

    /**
     *
     * @param user user the owner of the new card.
     * @param card the card to add to the database.
     */
    void putCard(IUser user, RequestCard card);
}
