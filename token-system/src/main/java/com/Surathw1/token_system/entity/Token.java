package com.Surathw1.token_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private LocalDateTime eventDateTime;

    private String category;

    @Column(nullable = false)
    private BigDecimal price;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    @Column(nullable = false)
    private String vendorId;

    private String customerId;

    // No-arg constructor required by JPA
    public Token() {
        this.ticketId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
        this.status = Status.AVAILABLE;
    }

    public enum Status {
        AVAILABLE, RESERVED, PURCHASED, CANCELLED
    }

    public static class Builder {
        private String eventName;
        private LocalDateTime eventDateTime;
        private String category;
        private BigDecimal price;
        private String seatNumber;
        private Status status = Status.AVAILABLE; // Default status
        private String vendorId;
        private String customerId;

        public Builder eventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public Builder eventDateTime(LocalDateTime eventDateTime) {
            if (eventDateTime.isBefore(LocalDateTime.now()))
                throw new IllegalArgumentException("Event date must be in the future.");
            this.eventDateTime = eventDateTime;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder price(BigDecimal price) {
            if (price.compareTo(BigDecimal.ZERO) < 0)
                throw new IllegalArgumentException("Price cannot be negative.");
            this.price = price;
            return this;
        }

        public Builder seatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        public Builder vendorId(String vendorId) {
            this.vendorId = vendorId;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Token build() {
            if (eventName == null || eventDateTime == null || price == null || vendorId == null)
                throw new IllegalStateException("Required fields: eventName, eventDateTime, price, vendorId");

            Token token = new Token();
            token.eventName = this.eventName;
            token.eventDateTime = this.eventDateTime;
            token.category = this.category;
            token.price = this.price;
            token.seatNumber = this.seatNumber;
            token.status = this.status;
            token.vendorId = this.vendorId;
            token.customerId = this.customerId;
            token.lastModifiedAt = LocalDateTime.now();

            return token;
        }
    }

    private Token(Builder builder) {
        this.ticketId = UUID.randomUUID().toString();
        this.eventName = builder.eventName;
        this.eventDateTime = builder.eventDateTime;
        this.category = builder.category;
        this.price = builder.price;
        this.seatNumber = builder.seatNumber;
        this.status = builder.status;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
        this.vendorId = builder.vendorId;
        this.customerId = builder.customerId;
    }

    // Getters and setters (omitted for brevity, but recommended to add)

    public Token updateStatus(Status newStatus, String customerId) {
        Token updatedToken = new Token();
        updatedToken.eventName = this.eventName;
        updatedToken.eventDateTime = this.eventDateTime;
        updatedToken.category = this.category;
        updatedToken.price = this.price;
        updatedToken.seatNumber = this.seatNumber;
        updatedToken.vendorId = this.vendorId;
        updatedToken.customerId = customerId;
        updatedToken.status = newStatus;
        updatedToken.createdAt = this.createdAt;
        updatedToken.lastModifiedAt = LocalDateTime.now();

        return updatedToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token ticket = (Token) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDateTime=" + eventDateTime +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", seatNumber='" + seatNumber + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                ", vendorId='" + vendorId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}