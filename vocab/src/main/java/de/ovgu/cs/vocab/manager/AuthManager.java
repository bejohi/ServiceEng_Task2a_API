package de.ovgu.cs.vocab.manager;

import de.ovgu.cs.vocab.database.repositories.IUserRepository;
import de.ovgu.cs.vocab.database.tables.DbUser;
import de.ovgu.cs.vocab.model.IUser;
import de.ovgu.cs.vocab.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthManager implements IAuthManager{

    private final IUserRepository userRepository;
    private Logger log = LoggerFactory.getLogger(AuthManager.class);

    @Autowired
    public AuthManager(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public IUser authenticate(String apiKey) {
        Optional<DbUser> userOpt = this.userRepository.findByApiKey(apiKey);
        if(!userOpt.isPresent()){
            log.warn("User with key " + apiKey + " NOT authorized/ found in DB");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        DbUser dbUser = userOpt.get();
        return new User(dbUser.getUsername(),dbUser.getApiKey());
    }

    @Override
    public Optional<String> addUser(String userName) {
        if(userName == null || userName.isEmpty()){
            log.warn("Empty username transmitted");
            return Optional.empty();
        }

        Optional<DbUser> userOptional = this.userRepository.findByUsername(userName);
        if(userOptional.isPresent()){
            log.warn("User with username " + userName + " was already stored in the database");
            return Optional.empty();
        }
        final String apiKey = UUID.randomUUID().toString().replace("-", "");
        DbUser newUser = new DbUser();
        newUser.setUsername(userName);
        newUser.setApiKey(apiKey);
        newUser = this.userRepository.saveAndFlush(newUser);

        if(newUser.getId() == 0){
            log.warn("Storing new user failed.");
            return Optional.empty();
        }
        return Optional.of(newUser.getApiKey());
    }
}
