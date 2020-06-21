package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.model.ResponseCard;
import de.ovgu.cs.vocab.model.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VocManager implements IVocManager {


    @Override
    public List<ResponseCard> getAllCardsForUser(User user) {
        return Collections.emptyList();
    }
}
