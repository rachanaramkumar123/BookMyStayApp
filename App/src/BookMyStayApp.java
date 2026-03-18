/**
 * Book My Stay App
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 * @author Rachana
 * @version 11.0
 */

import java.util.*;

// Reservation
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

// Thread-safe Booking Queue
class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.offer(r);
    }

    public synchronized Reservation getRequest() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Inventory (Shared Resource)
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
    }

    public synchronized boolean allocateRoom(String type) {
        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {
            inventory.put(type, available - 1);
            return true;
        }
        return false;
    }

    public synchronized void display() {
        System.out.println("Final Inventory: " + inventory);
    }
}

// Booking Processor (Thread)
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (true) {
            Reservation r;

            synchronized (queue) {
                if (queue.isEmpty()) break;
                r = queue.getRequest();
            }

            if (r != null) {
                boolean success = inventory.allocateRoom(r.getRoomType());

                if (success) {
                    System.out.println(Thread.currentThread().getName() +
                            " booked room for " + r.getGuestName());
                } else {
                    System.out.println(Thread.currentThread().getName() +
                            " failed booking for " + r.getGuestName());
                }
            }
        }
    }
}

// Main
class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        BOOK MY STAY APPLICATION          ");
        System.out.println("==========================================");
        System.out.println("Version : v11.0");
        System.out.println("------------------------------------------");

        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();

        // Simulate concurrent requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room"));

        // Multiple threads
        Thread t1 = new BookingProcessor(queue, inventory);
        Thread t2 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventory.display();

        System.out.println("==========================================");
        System.out.println("Concurrent booking simulation completed.");
    }
}