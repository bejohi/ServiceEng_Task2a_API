package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.database.IUserRepo;
import de.ovgu.cs.vocab.model.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthManager implements IAuthManager{

    private final IUserRepo userRepo;
    private Logger log = LoggerFactory.getLogger(AuthManager.class);

    @Autowired
    public AuthManager(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public IUser authenticate(String apiKey) {
        IUser user = this.userRepo.getUser(apiKey);
        if(user == null){
            log.warn("User with key " + apiKey + " NOT authorized");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        log.info("User " + user.getUsername() + " authorized.");
        return user;
    }
}
