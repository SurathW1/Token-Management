package com.Surathw1.token_system.entity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ticketPool {
    private final BlockingQueue<Token> Token;

    public ticketPool(int maxCapacity) {
        this.Token = new LinkedBlockingQueue<>(maxCapacity);
    }

    public BlockingQueue<Token> getToken() {
        return Token;
    }
}
