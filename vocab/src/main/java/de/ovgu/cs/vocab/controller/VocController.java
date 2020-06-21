package de.ovgu.cs.vocab.controller;

import de.ovgu.cs.vocab.manager.IVocManager;
import de.ovgu.cs.vocab.model.ResponseCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The main controller for the public available API endpoints.
 */
@RestController
public class VocController {

    private final IVocManager vocManager;

    @Autowired
    public VocController(IVocManager vocManager) {
        this.vocManager = vocManager;
    }

    @GetMapping("/allCardsForUser")
    public ResponseEntity<List<ResponseCard>> getAllCardsForUser(){
        return ResponseEntity.ok(this.vocManager.getAllCardsForUser(null));
    }
}
