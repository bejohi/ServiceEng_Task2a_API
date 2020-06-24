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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VocManager implements IVocManager{

    private final IUserRepository userRepository;
    private final ICardRepository cardRepository;

    private Logger log = LoggerFactory.getLogger(VocManager.class);

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
        return null;
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
    public void updateCard(IUser user, long id, RequestCard card) {

    }

    @Override
    public void deleteCard(IUser user, long cardId) {

    }

    @Override
    public void moveCard(IUser user, RequestMoveCard moveCard) {

    }

    private DbUser getDbUser(IUser userIn){
        List<DbUser> users = this.userRepository.findByApiKey(userIn.getApiKey());
        if(users.size() != 1){
            log.error("A user was not found. That's bad.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return users.get(0);
    }
}
