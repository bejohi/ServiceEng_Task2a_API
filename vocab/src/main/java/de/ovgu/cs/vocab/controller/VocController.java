package de.ovgu.cs.vocab.controller;

import de.ovgu.cs.vocab.manager.IAuthManager;
import de.ovgu.cs.vocab.manager.IVocManager;
import de.ovgu.cs.vocab.model.IUser;
import de.ovgu.cs.vocab.model.ResponseCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The main controller for the public available API endpoints.
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

    @GetMapping("/allCardsForUser")
    public ResponseEntity<List<ResponseCard>> getAllCardsForUser(@RequestParam(value="apikey") String apiKey){
        IUser user = this.authManager.authenticate(apiKey);
        return ResponseEntity.ok(this.vocManager.getAllCardsForUser(user));
    }
}
