package com.Surathw1.token_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.apache.logging.log4j.Logger;

import org.junit.platform.commons.logging.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Token {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(Token.class);

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long ticketId;

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
        this.ticketId = Long.parseLong(UUID.randomUUID().toString());
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
        this.status = Status.AVAILABLE;
        logger.info("Created new Token with ticketId: {}", ticketId);
    }

    public void setId(long id) {
        this.id = id;
        logger.debug("Set id for token: {}", id);
    }

    public enum Status {
        AVAILABLE, RESERVED, PURCHASED, CANCELLED
    }

    // Getters and Setters with logging
    public long getTicketId() {
        logger.debug("Getting ticketId: {}", ticketId);
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
        logger.debug("Set ticketId: {}", ticketId);
    }

    public String getEventName() {
        logger.debug("Getting eventName: {}", eventName);
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
        logger.debug("Set eventName: {}", eventName);
    }

    public LocalDateTime getEventDateTime() {
        logger.debug("Getting eventDateTime: {}", eventDateTime);
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
        logger.debug("Set eventDateTime: {}", eventDateTime);
    }

    public String getCategory() {
        logger.debug("Getting category: {}", category);
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        logger.debug("Set category: {}", category);
    }

    public BigDecimal getPrice() {
        logger.debug("Getting price: {}", price);
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        logger.debug("Set price: {}", price);
    }

    public String getSeatNumber() {
        logger.debug("Getting seatNumber: {}", seatNumber);
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        logger.debug("Set seatNumber: {}", seatNumber);
    }

    public Status getStatus() {
        logger.debug("Getting status: {}", status);
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        logger.debug("Set status: {}", status);
    }

    public LocalDateTime getCreatedAt() {
        logger.debug("Getting createdAt: {}", createdAt);
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        logger.debug("Set createdAt: {}", createdAt);
    }

    public LocalDateTime getLastModifiedAt() {
        logger.debug("Getting lastModifiedAt: {}", lastModifiedAt);
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
        logger.debug("Set lastModifiedAt: {}", lastModifiedAt);
    }

    public String getVendorId() {
        logger.debug("Getting vendorId: {}", vendorId);
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
        logger.debug("Set vendorId: {}", vendorId);
    }

    public String getCustomerId() {
        logger.debug("Getting customerId: {}", customerId);
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
        logger.debug("Set customerId: {}", customerId);
    }

    // updateStatus method with logging
    public Token updateStatus(Status newStatus, String customerId) {
        logger.info("Updating status for token with ticketId: {} from {} to {}", ticketId, status, newStatus);
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
        logger.debug("Comparing token with ticketId: {} to another token", ticketId);
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
