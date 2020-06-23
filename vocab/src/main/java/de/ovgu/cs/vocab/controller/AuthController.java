package de.ovgu.cs.vocab.controller;

import de.ovgu.cs.vocab.manager.IAuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Provides API endpoints to register new users.
 */
@RestController
public class AuthController {

    private final IAuthManager authManager;

    @Autowired
    public AuthController(IAuthManager authManager) {
        this.authManager = authManager;
    }

    @PutMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestParam("userName") String userName){
        Optional<String> apiKeyOptional = this.authManager.addUser(userName);
        return apiKeyOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("A user with the same username was already registered in the system."));
    }

}
