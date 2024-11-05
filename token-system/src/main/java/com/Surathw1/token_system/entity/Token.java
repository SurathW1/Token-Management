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
    private Long id;

    // No-arg constructor required by JPA
    public Token() {
        this.ticketId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
        this.status = Status.AVAILABLE;
    }

    public void setId(long id) {
        this.id=id;
    }

    public enum Status {
        AVAILABLE, RESERVED, PURCHASED, CANCELLED
    }

    // Getters and Setters
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // Remaining methods: Builder, updateStatus, equals, hashCode, toString

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
