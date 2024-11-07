package com.Surathw1.token_system.entity;

import java.util.concurrent.BlockingQueue;


public class Vendor implements Runnable {

    private final BlockingQueue<Token>ticketPool;
    private final int ticketsPerRelease;
    private final int releaseInterval;
    private volatile boolean running = true;

    public Vendor(BlockingQueue<Token> ticketPool, int ticketsPerRelease, int releaseInterval) {
        this.ticketPool = ticketPool;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
    }

    public void stop(){
        running = false;
    }
    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < ticketsPerRelease; i++) {
                    Token ticket = new Token(); // create a new token
                    ticketPool.put(ticket); // add token to pool

                }
                Thread.sleep(releaseInterval * 1000L); // sleep for release interval
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }
}
