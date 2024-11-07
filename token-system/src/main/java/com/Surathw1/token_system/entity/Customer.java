package com.Surathw1.token_system.entity;



import java.util.concurrent.BlockingQueue;



public class Customer implements Runnable {


    private final BlockingQueue<Token> ticketPool;
    private final int retrievalInterval;
    private volatile boolean running = true;

    public Customer(BlockingQueue<Token> ticketPool, int retrievalInterval) {
        this.ticketPool = ticketPool;
        this.retrievalInterval = retrievalInterval;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Token ticket = ticketPool.take(); // retrieve ticket from pool

                Thread.sleep(retrievalInterval * 1000L); // sleep for retrieval interval
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }
}
