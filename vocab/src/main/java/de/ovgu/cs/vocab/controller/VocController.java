package de.ovgu.cs.vocab.controller;

import de.ovgu.cs.vocab.manager.IAuthManager;
import de.ovgu.cs.vocab.manager.IVocManager;
import de.ovgu.cs.vocab.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The main controller for the public available vocabulary card API endpoints.
 */
@CrossOrigin("*")
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
    @GetMapping("/cards")
    public ResponseEntity<List<ResponseCard>> getAllCardsForUser(@RequestParam(value="apikey") String apiKey){
        IUser user = this.authManager.authenticate(apiKey);
        return ResponseEntity.ok(this.vocManager.getAllCardsForUser(user));
    }

    /**
     *
     * @param apiKey the API-key to identify the current user.
     * @return a randomly selected next card to test for the user.
     */
    @GetMapping("/nextCard")
    public ResponseEntity<ResponseCard> getNextCard(@RequestParam(value="apikey") String apiKey){
        IUser user = this.authManager.authenticate(apiKey);
        ResponseCard card = this.vocManager.getNextCardForUser(user);
        return ResponseEntity.ok(card);
    }

    /**
     * Creates a new card.
     * @param apiKey the API-key to identify the current user.
     * @param card the card to put into the database.
     * @return a message of the success or failure of the put operation.
     */
    @PutMapping("/cards")
    public ResponseEntity<String> putCard(@RequestBody RequestCard card,
                                          @RequestParam(value = "apikey",required = false) String apiKey){
        IUser user = this.authManager.authenticate(apiKey);
        this.vocManager.putCard(user,card);
        return ResponseEntity.ok("Card successfully added.");
    }

    /**
     * Changes values of the card with the given value.
     * @param apiKey the API-key to identify the current user.
     * @param card the card which should be changend by the request.
     * @return a message of the success or failure of the put operation.
     */
    @PostMapping("/cards/{cardId}")
    public ResponseEntity<GenericResponse> postCard(@PathVariable long cardId,
                                                    @RequestBody RequestCard card,
                                                    @RequestParam(value = "apikey",required = false) String apiKey){
        IUser user = this.authManager.authenticate(apiKey);
        this.vocManager.updateCard(user,cardId, card);
        return ResponseEntity.ok(GenericResponse.ok("Card successfully changed."));
    }

    /**
     * Deletes the card with the given value, in case the user is authorized.
     * @param apiKey the API-key to identify the current user.
     * @param cardId the unique ID of the card to delete.
     * @return a message of the success or failure of the put operation.
     */
    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<GenericResponse> deleteCard(@PathVariable long cardId,
                                             @RequestParam(value = "apikey",required = false) String apiKey){
        IUser user = this.authManager.authenticate(apiKey);
        this.vocManager.deleteCard(user,cardId);
        return ResponseEntity.ok(GenericResponse.ok("Card successfully deleted"));
    }

    /**
     * Moves the given card to another level
     * @param apiKey the API-key to identify the current user.
     * @return a message of the success or failure of the put operation.
     */
    @PatchMapping("/cards/{cardId}")
    public ResponseEntity<GenericResponse> moveCard(@PathVariable long cardId,
                                                    @RequestParam(value = "newLevel",required = false) int newLevel,
                                           @RequestParam(value = "apikey",required = false) String apiKey){
        IUser user = this.authManager.authenticate(apiKey);
        this.vocManager.moveCard(user,newLevel,cardId);
        return ResponseEntity.ok(GenericResponse.ok("Level of card successfully changed."));
    }
}
