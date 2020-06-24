package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.database.repositories.ICardRepository;
import de.ovgu.cs.vocab.database.repositories.IUserRepository;
import de.ovgu.cs.vocab.database.tables.DbCard;
import de.ovgu.cs.vocab.database.tables.DbUser;
import de.ovgu.cs.vocab.model.IUser;
import de.ovgu.cs.vocab.model.RequestCard;
import de.ovgu.cs.vocab.model.RequestMoveCard;
import de.ovgu.cs.vocab.model.ResponseCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class VocManager implements IVocManager{

    private final IUserRepository userRepository;
    private final ICardRepository cardRepository;

    private final Logger log = LoggerFactory.getLogger(VocManager.class);

    @Autowired
    public VocManager(IUserRepository userRepository, ICardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public List<ResponseCard> getAllCardsForUser(IUser user) {
        DbUser dbUser = this.getDbUser(user);
        List<ResponseCard> responseList = dbUser.getCards().stream()
                .map(card -> new ResponseCard(card.getQuestion(),card.getAnswer(),card.getLevel(),card.getId()))
                .collect(Collectors.toList());
        return responseList;
    }

    @Override
    public ResponseCard getNextCardForUser(IUser user) {
        DbUser dbUser = this.getDbUser(user);
        List<DbCard> cards = new ArrayList<>(dbUser.getCards());
        if(cards.size() == 0){
            return new ResponseCard("","",0,0);
        }
        int index = ThreadLocalRandom.current().nextInt(0, cards.size());
        DbCard selectedCard = cards.get(index);
        return new ResponseCard(selectedCard.getQuestion(),selectedCard.getAnswer(),
                selectedCard.getLevel(),selectedCard.getId());
    }

    @Override
    public void putCard(IUser user, RequestCard card) {
        DbUser dbUser = this.getDbUser(user);
        DbCard dbCard = new DbCard();
        dbCard.setAnswer(card.getAnswer());
        dbCard.setQuestion(card.getQuestion());
        dbCard.setLevel(dbCard.getLevel());
        dbCard.setUser(dbUser);
        DbCard result = this.cardRepository.saveAndFlush(dbCard);
        if(result.getId() <= 0){
            log.warn("Saving of new card failed");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void updateCard(IUser user, long cardId, RequestCard card) {
        DbCard dbCard = this.getCardIfUserIsAuthorized(user,cardId);
        dbCard.setLevel(card.getLevel());
        dbCard.setQuestion(card.getQuestion());
        dbCard.setAnswer(card.getAnswer());
        this.cardRepository.saveAndFlush(dbCard);
    }

    @Override
    public void deleteCard(IUser user, long cardId) {
        DbCard dbCard = this.getCardIfUserIsAuthorized(user,cardId);
        this.cardRepository.delete(dbCard);
    }

    @Override
    public void moveCard(IUser user, int newLevel, long cardId){
        DbCard dbCard = this.getCardIfUserIsAuthorized(user,cardId);
        dbCard.setLevel(newLevel);
        this.cardRepository.saveAndFlush(dbCard);
    }

    /**
     * Checks that the given user is present in the database and authorized for the card with the given ID.
     * In case no card is found for the given ID an exception is thrown.
     */
    public DbCard getCardIfUserIsAuthorized(IUser user,long cardId){
        Optional<DbCard> dbCardOptional = this.cardRepository.findById(cardId);
        DbUser dbUser = this.getDbUser(user);
        if(!dbCardOptional.isPresent()) {
            log.warn("No card with id " + cardId + " found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        DbCard dbCard = dbCardOptional.get();
        if(dbCard.getUser().getId() != dbUser.getId()){
            log.warn("User " + dbUser.getId() + "not authorized for card " + cardId);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return dbCard;
    }

    private DbUser getDbUser(IUser userIn){
        Optional<DbUser> dbUserOptional = this.userRepository.findByApiKey(userIn.getApiKey());
        if(!dbUserOptional.isPresent()){
            log.error("A user was not found. That's bad.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return dbUserOptional.get();
    }
}
