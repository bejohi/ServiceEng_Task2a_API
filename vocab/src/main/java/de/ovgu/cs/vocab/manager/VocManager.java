package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.model.IUser;
import de.ovgu.cs.vocab.model.ResponseCard;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VocManager implements IVocManager {


    @Override
    public List<ResponseCard> getAllCardsForUser(IUser user) {
        return Collections.emptyList();
    }
}
