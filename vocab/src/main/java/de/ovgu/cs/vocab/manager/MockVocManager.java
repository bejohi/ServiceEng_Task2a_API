package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.model.IUser;
import de.ovgu.cs.vocab.model.RequestCard;
import de.ovgu.cs.vocab.model.ResponseCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockVocManager implements IVocManager{

    private static List<ResponseCard> cards = new ArrayList<>();

    {
        cards.add(new ResponseCard("What is 1 + 1","2",0,1));
        cards.add(new ResponseCard("What is 1 + 2","3",0,2));
        cards.add(new ResponseCard("What is 1 + 3","4",0,3));
        cards.add(new ResponseCard("What is 1 + 4","5",0,4));
        cards.add(new ResponseCard("What is 1 + 5","6",0,5));
        cards.add(new ResponseCard("What is 1 + 6","7",0,6));
        cards.add(new ResponseCard("What is 1 + 7","8",0,7));
    }
    static long idCounter = 9;

    @Override
    public List<ResponseCard> getAllCardsForUser(IUser user) {
        return cards;
    }

    @Override
    public ResponseCard getNextCardForUser(IUser user) {
        return new ResponseCard("What is 1 + 2","3",0,8);
    }

    @Override
    public void putCard(IUser user, RequestCard card) {
        cards.add(new ResponseCard("What is 1 + 7","8",0,idCounter++));
    }
}
