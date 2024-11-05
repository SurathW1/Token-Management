package com.Surathw1.token_system.service;

import com.Surathw1.token_system.entity.Token;
import com.Surathw1.token_system.entity.Token.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TokenService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String tokenFilePath = "data/tokens.json"; // Specify the file path for persistence
    private final Map<Long,Token> ticketStore=new HashMap<>();//In-Memory store to hold Token Objects, with Ticket ID as the Key


//Counter to assign Unique ID'S to each Ticket
    private long currentId=1;
    public Token addToken(Token token) {
        token.setId(currentId++);
    }
    // Ensure the data directory exists for token storage
    public TokenService() {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir(); // Create directory if not exists
        }
    }

    // Create a new token
    public synchronized Token createToken(Token token) throws IOException {
        // Generate a unique ticket ID if not provided
        token.setTicketId(UUID.randomUUID().toString());
        token.setCreatedAt(LocalDateTime.now());
        token.setLastModifiedAt(LocalDateTime.now());

        List<Token> tokens = loadTokensFromFile();
        tokens.add(token);
        saveTokensToFile(tokens);

        return token;
    }

    // Get token by ID
    public synchronized Optional<Token> getTokenById(String ticketId) throws IOException {
        return loadTokensFromFile().stream()
                .filter(token -> ticketId.equals(token.getTicketId()))
                .findFirst();
    }

    // Get all tokens
    public synchronized List<Token> getAllTokens() throws IOException {
        return loadTokensFromFile();
    }

    // Get tokens by status
    public synchronized List<Token> getTokensByStatus(Status status) throws IOException {
        List<Token> tokens = loadTokensFromFile();
        List<Token> filteredTokens = new ArrayList<>();
        for (Token token : tokens) {
            if (token.getStatus() == status) {
                filteredTokens.add(token);
            }
        }
        return filteredTokens;
    }

    // Get tokens by customer ID
    public synchronized List<Token> getTokensByCustomerId(String customerId) throws IOException {
        List<Token> tokens = loadTokensFromFile();
        List<Token> filteredTokens = new ArrayList<>();
        for (Token token : tokens) {
            if (customerId.equals(token.getCustomerId())) {
                filteredTokens.add(token);
            }
        }
        return filteredTokens;
    }

    // Get tokens by event name
    public synchronized List<Token> getTokensByEventName(String eventName) throws IOException {
        List<Token> tokens = loadTokensFromFile();
        List<Token> filteredTokens = new ArrayList<>();
        for (Token token : tokens) {
            if (eventName.equals(token.getEventName())) {
                filteredTokens.add(token);
            }
        }
        return filteredTokens;
    }

    // Update token status
    public synchronized Token updateTokenStatus(String ticketId, Status newStatus, String customerId) throws IOException {
        List<Token> tokens = loadTokensFromFile();
        for (Token token : tokens) {
            if (ticketId.equals(token.getTicketId())) {
                token.setStatus(newStatus);
                token.setCustomerId(customerId);
                token.setLastModifiedAt(LocalDateTime.now());
                saveTokensToFile(tokens); // Save updated list back to file
                return token;
            }
        }
        throw new RuntimeException("Token not found with ID: " + ticketId);
    }

    // Update token information
    public synchronized Token updateToken(String ticketId, Token tokenDetails) throws IOException {
        List<Token> tokens = loadTokensFromFile();
        for (Token token : tokens) {
            if (ticketId.equals(token.getTicketId())) {
                token.setEventName(tokenDetails.getEventName());
                token.setEventDateTime(tokenDetails.getEventDateTime());
                token.setCategory(tokenDetails.getCategory());
                token.setPrice(tokenDetails.getPrice());
                token.setSeatNumber(tokenDetails.getSeatNumber());
                token.setVendorId(tokenDetails.getVendorId());
                token.setCustomerId(tokenDetails.getCustomerId());
                token.setStatus(tokenDetails.getStatus());
                token.setLastModifiedAt(LocalDateTime.now());
                saveTokensToFile(tokens); // Save updated list back to file
                return token;
            }
        }
        throw new RuntimeException("Token not found with ID: " + ticketId);
    }

    // Delete token
    public synchronized void deleteToken(String ticketId) throws IOException {
        List<Token> tokens = loadTokensFromFile();
        tokens.removeIf(token -> ticketId.equals(token.getTicketId()));
        saveTokensToFile(tokens); // Save updated list back to file
    }

    // Helper method to load tokens from JSON file
    private List<Token> loadTokensFromFile() throws IOException {
        File file = new File(tokenFilePath);
        if (!file.exists()) {
            return new ArrayList<>(); // Return empty list if file doesn't exist
        }
        return objectMapper.readValue(file, new TypeReference<List<Token>>() {});
    }

    // Helper method to save tokens to JSON file
    private void saveTokensToFile(List<Token> tokens) throws IOException {
        objectMapper.writeValue(new File(tokenFilePath), tokens);
    }
}
