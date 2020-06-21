package de.ovgu.cs.vocab.controller;

import de.ovgu.cs.vocab.manager.IAuthManager;
import de.ovgu.cs.vocab.manager.IVocManager;
import de.ovgu.cs.vocab.model.IUser;
import de.ovgu.cs.vocab.model.RequestCard;
import de.ovgu.cs.vocab.model.ResponseCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The main controller for the public available vocabulary card API endpoints.
 */
@RestController
public class VocController {

    private final IVocManager vocManager;
    private final IAuthManager authManager;

    @Autowired
    public VocController(IVocManager vocManager, IAuthManager authManager) {
        this.vocManager = vocManager;
        this.authManager = authManager;
    }

    /**
     * @param apiKey the API-key to identify the current user.
     * @return a list of all cards which are stored for the given user.
     */
    @GetMapping("/allCards")
    public ResponseEntity<List<ResponseCard>> getAllCardsForUser(@RequestParam(value="apikey") String apiKey){
        IUser user = this.authManager.authenticate(apiKey);
        return ResponseEntity.ok(this.vocManager.getAllCardsForUser(user));
    }

    /**
     *
     * @param apiKey the API-key to identify the current user.
     * @return a randomly selected next card to test for the user.
     */
    @GetMapping("nextCard")
    public ResponseEntity<ResponseCard> getNextCard(@RequestParam(value="apikey") String apiKey){
        IUser user = this.authManager.authenticate(apiKey);
        ResponseCard card = this.vocManager.getNextCardForUser(user);
        return ResponseEntity.ok(card);
    }

    /**
     *
     * @param apiKey the API-key to identify the current user.
     * @param card the card to put into the database.
     * @return a message of the success or failure of the put operation.
     */
    @PutMapping("putCard")
    public ResponseEntity<String> putCard(@RequestParam(value="apikey") String apiKey, @RequestBody RequestCard card){
        IUser user = this.authManager.authenticate(apiKey);
        this.vocManager.putCard(user,card);
        return ResponseEntity.ok("Card successfully added.");
    }
}
