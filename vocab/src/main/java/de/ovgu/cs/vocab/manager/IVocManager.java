package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.model.IUser;
import de.ovgu.cs.vocab.model.RequestCard;
import de.ovgu.cs.vocab.model.RequestMoveCard;
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
     * Creates a new card and stores it in the system.
     * @param user user the owner of the new card.
     * @param card the card to add to the database.
     */
    void putCard(IUser user, RequestCard card);

    /**
     * Changes the values of a given card (must already be stored with the given ID).
     * @param user user the owner of the card.
     * @param card the card to add to the database.
     */
    void updateCard(IUser user, long id, RequestCard card);

    /**
     * Deletes the card with the given ID in case the user is authorized for the operation.
     * @param user user the owner of the card.
     * @param cardId the unique ID of the card to delete.
     */
    void deleteCard(IUser user, long cardId);

    /**
     * Moves a card with a given ID from one level to anothe.
     * @param user user the owner of the card.
     * @param moveCard an object which holds all necessary information.
     */
    void moveCard(IUser user, RequestMoveCard moveCard);
}
