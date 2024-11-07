package com.Surathw1.token_system;

public class TicketSystemTest {

    public static void main(String[] args) {
        int maxCapacity = 100;
        int ticketsPerRelease = 10;
        int releaseInterval = 2; // seconds
        int retrievalInterval = 1; // seconds

        TicketPool ticketPool = new TicketPool(maxCapacity);

        Vendor vendor1 = new Vendor(ticketPool.getTickets(), ticketsPerRelease, releaseInterval);
        Vendor vendor2 = new Vendor(ticketPool.getTickets(), ticketsPerRelease, releaseInterval);

        Customer customer1 = new Customer(ticketPool.getTickets(), retrievalInterval);
        Customer customer2 = new Customer(ticketPool.getTickets(), retrievalInterval);

        Thread vendorThread1 = new Thread(vendor1);
        Thread vendorThread2 = new Thread(vendor2);
        Thread customerThread1 = new Thread(customer1);
        Thread customerThread2 = new Thread(customer2);

        vendorThread1.start();
        vendorThread2.start();
        customerThread1.start();
        customerThread2.start();

        // Let the system run for some time, then stop threads
        try {
            Thread.sleep(10000); // run for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        vendor1.stop();
        vendor2.stop();
        customer1.stop();
        customer2.stop();

        vendorThread1.interrupt();
        vendorThread2.interrupt();
        customerThread1.interrupt();
        customerThread2.interrupt();
    }
}
