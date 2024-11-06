package com.Surathw1.token_system.entity;

import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vendor {
    private static Logger log = LoggerFactory.getLogger(Vendor.class);
    private final BlockingQueue<Token>ticketPool;
    private final int ticketsPerRelease;
    private final int releaseInterval;

    public Vendor(BlockingQueue<Token> ticketPool, int ticketsPerRelease, int releaseInterval) {
        this.ticketPool = ticketPool;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < ticketsPerRelease; i++) {
                    Token ticket = new Token(); // create a new token
                    ticketPool.put(ticket); // add token to pool
                    logger.info("Vendor added ticket: {}", ticket.getTicketId());
                }
                Thread.sleep(releaseInterval * 1000L); // sleep for release interval
            } catch (InterruptedException e) {
                logger.error("Vendor interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
