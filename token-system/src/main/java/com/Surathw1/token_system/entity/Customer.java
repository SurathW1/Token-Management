package com.Surathw1.token_system.entity;

import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;


public class Customer implements Runnable {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(Customer.class);
    private final BlockingQueue<Token> ticketPool;
    private final int retrievalInterval;

    public Customer(BlockingQueue<Token> ticketPool, int retrievalInterval) {
        this.ticketPool = ticketPool;
        this.retrievalInterval = retrievalInterval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Token ticket = ticketPool.take(); // retrieve ticket from pool
                logger.info("Customer retrieved ticket: {}", ticket.getTicketId());
                Thread.sleep(retrievalInterval * 1000L); // sleep for retrieval interval
            } catch (InterruptedException e) {
                logger.error("Customer interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
