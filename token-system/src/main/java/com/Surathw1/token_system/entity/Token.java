package com.Surathw1.token_system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Token {
    private final String ticketId;
    private final String eventName;
    private final LocalDateTime eventDateTime;
    private final String category;
    private final BigDecimal price;
    private final String seatNumber;
    private final Status status;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
    private final String vendorId;
    private final String customerId;

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

        public Builder eventName(String eventName) { this.eventName = eventName; return this; }
        public Builder eventDateTime(LocalDateTime eventDateTime) {
            if (eventDateTime.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Event date must be in the future.");
            this.eventDateTime = eventDateTime;
            return this;
        }
        public Builder category(String category) { this.category = category; return this; }
        public Builder price(BigDecimal price) {
            if (price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Price cannot be negative.");
            this.price = price;
            return this;
        }
        public Builder seatNumber(String seatNumber) { this.seatNumber = seatNumber; return this; }
        public Builder vendorId(String vendorId) { this.vendorId = vendorId; return this; }
        public Builder customerId(String customerId) { this.customerId = customerId; return this; }


        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Token build() {
            if (eventName == null || eventDateTime == null || price == null || vendorId == null)
                throw new IllegalStateException("Required fields: eventName, eventDateTime, price, vendorId");
            return new Token(this);
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


    public Token updateStatus(Status newStatus, String customerId) {
        return new Token.Builder()
                .eventName(this.eventName)
                .eventDateTime(this.eventDateTime)
                .category(this.category)
                .price(this.price)
                .seatNumber(this.seatNumber)
                .vendorId(this.vendorId)
                .customerId(customerId)
                .status(newStatus)
                .build();
    }
    // Check if the objects are the same instance
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token ticket = (Token) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }
    // Generate hash code based on ticketId
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

