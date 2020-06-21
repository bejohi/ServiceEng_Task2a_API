package de.ovgu.cs.vocab.database;

import de.ovgu.cs.vocab.model.IUser;
import de.ovgu.cs.vocab.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock service for the IUserRepo.
 */
@Service
public class MockUserRepo implements IUserRepo {

    private static List<User> users;
    {
        users = new ArrayList<>();
        User testUser = new User("TestUser","secretKey");
        users.add(testUser);
    }

    @Override
    public synchronized IUser getUser(String apiKey) {
        for(User user : users){
            if(user.getApiKey().equals(apiKey)){
                return user;
            }
        }
        return null;
    }
}
