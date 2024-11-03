package com.Surathw1.token_system.service;

import com.Surathw1.token_system.entity.Token;
import com.Surathw1.token_system.entity.Token.Status;
import com.Surathw1.token_system.reposistory.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // Create new token
    public Token createToken(Token.Builder builder) {
        Token token = builder.build();
        return tokenRepository.save(token);
    }

    // Get token by ID
    public Optional<Token> getTokenById(String ticketId) {
        return tokenRepository.findById(ticketId);
    }

    // Get all tokens
    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    // Get tokens by status
    public List<Token> getTokensByStatus(Status status) {
        return tokenRepository.findByStatus(status.toString());
    }

    // Get tokens by customer ID
    public List<Token> getTokensByCustomerId(Integer customerId) {
        return tokenRepository.findTokensByCustomerId(customerId);
    }

    // Get tokens by event name
    public List<Token> getTokensByeventName(String eventName) {
        return tokenRepository.findTokensByEventName(eventName);
    }

    // Get tokens by customer ID, status, and event name
    public List<Token> getTokensByCustomerIdAndStatusAndeventName(
            Integer customerId,
            Status status,
            String eventName) {
        return tokenRepository.findTokensByCustomerIdAndStatusAndEventName(
                customerId,
                status.toString(),
                eventName
        );
    }

    // Update token status
    public Token updateTokenStatus(String ticketId, Status newStatus, String customerId) {
        Token existingToken = tokenRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Token not found with ID: " + ticketId));

        Token updatedToken = existingToken.updateStatus(newStatus, customerId);
        return tokenRepository.save(updatedToken);
    }

    // Update token information
    public Token updateToken(String ticketId, Token.Builder builder) {
        Token existingToken = tokenRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Token not found with ID: " + ticketId));

        Token updatedToken = builder.build();
        return tokenRepository.save(updatedToken);
    }

    // Delete token
    public void deleteToken(String ticketId) {
        tokenRepository.deleteById(ticketId);
    }
}