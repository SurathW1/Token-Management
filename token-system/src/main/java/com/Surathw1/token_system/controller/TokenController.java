package com.Surathw1.token_system.controller;

import com.Surathw1.token_system.entity.Token;
import com.Surathw1.token_system.entity.Token.Status;
import com.Surathw1.token_system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // Create a new token
    @PostMapping
    public ResponseEntity<Token> createToken(@RequestBody Token token) {
        try {
            Token createdToken = tokenService.createToken(token);
            return new ResponseEntity<>(createdToken, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a token by ID
    @GetMapping("/{id}")
    public ResponseEntity<Token> getTokenById(@PathVariable("id") String ticketId) {
        try {
            Optional<Token> token = tokenService.getTokenById(ticketId);
            return token.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all tokens
    @GetMapping
    public ResponseEntity<List<Token>> getAllTokens() {
        try {
            List<Token> tokens = tokenService.getAllTokens();
            return new ResponseEntity<>(tokens, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get tokens by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Token>> getTokensByStatus(@PathVariable("status") Status status) {
        try {
            List<Token> tokens = tokenService.getTokensByStatus(status);
            return new ResponseEntity<>(tokens, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update token status
    @PutMapping("/{id}/status")
    public ResponseEntity<Token> updateTokenStatus(
            @PathVariable("id") String ticketId,
            @RequestParam("status") Status newStatus,
            @RequestParam("customerId") String customerId) {
        try {
            Token updatedToken = tokenService.updateTokenStatus(ticketId, newStatus, customerId);
            return new ResponseEntity<>(updatedToken, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update token details
    @PutMapping("/{id}")
    public ResponseEntity<Token> updateToken(
            @PathVariable("id") String ticketId,
            @RequestBody Token tokenDetails) {
        try {
            Token updatedToken = tokenService.updateToken(ticketId, tokenDetails);
            return new ResponseEntity<>(updatedToken, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a token
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteToken(@PathVariable("id") String ticketId) {
        try {
            tokenService.deleteToken(ticketId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}