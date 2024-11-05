package com.Surathw1.token_system.controller;

import com.Surathw1.token_system.entity.Token;
import com.Surathw1.token_system.entity.Token.Status;
import com.Surathw1.token_system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    private TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    //creation of token instance
    @PostMapping
    public ResponseEntity<Token> createToken(@RequestBody Token token) {
        Token createdToken = tokenService.createToken(token);
        return new ResponseEntity<>(createdToken, HttpStatus.CREATED);
    }

    //token return from ID
    @GetMapping("/{id}")
    public ResponseEntity<Token> getTokenById(@PathVariable("id") String tokenId) {
        Optional<Token> token = tokenService.getTokenById(tokenId);
        return token.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update a token by ID
    @PutMapping("/{id}")
    public ResponseEntity<Token> updateToken(@PathVariable("id") String tokenId, @RequestBody Token updatedToken) {
        try {
            Token token = tokenService.updateToken(tokenId, updatedToken);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    // Delete a token by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToken(@PathVariable("id") String tokenId) {
        try {
            tokenService.deleteToken(tokenId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Update the status of a token
    @PatchMapping("/{id}/status")
    public ResponseEntity<Token> updateTokenStatus(
            @PathVariable("id") String tokenId,
            @RequestParam Status status,
            @RequestParam(required = false) String customerId) {
        try {
            Token updatedToken = tokenService.updateTokenStatus(tokenId, status, customerId);
            return ResponseEntity.ok(updatedToken);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}