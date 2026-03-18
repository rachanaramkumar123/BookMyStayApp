/**
 * Book My Stay App
 * Use Case 10: Booking Cancellation & Inventory Rollback
 * @author Rachana
 * @version 10.0
 */

import java.util.*;

// Reservation
class Reservation {
    private String reservationId;
    private String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() { return reservationId; }
    public String getRoomType() { return roomType; }
}

// Inventory
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public void increaseRoom(String type) {
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }

    public void display() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}

// Booking History
class BookingHistory {
    private Map<String, Reservation> confirmedBookings = new HashMap<>();

    public void add(Reservation r) {
        confirmedBookings.put(r.getReservationId(), r);
    }

    public boolean exists(String id) {
        return confirmedBookings.containsKey(id);
    }

    public Reservation remove(String id) {
        return confirmedBookings.remove(id);
    }
}

// Cancellation Service
class CancellationService {

    private Stack<String> rollbackStack = new Stack<>();

    public void cancel(String reservationId, BookingHistory history, RoomInventory inventory) {

        if (!history.exists(reservationId)) {
            System.out.println("Cancellation Failed: Invalid Reservation ID");
            return;
        }

        Reservation r = history.remove(reservationId);

        // Push to rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory
        inventory.increaseRoom(r.getRoomType());

        System.out.println("Cancellation Successful for ID: " + reservationId);
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack (LIFO): " + rollbackStack);
    }
}

// Main
class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        BOOK MY STAY APPLICATION          ");
        System.out.println("==========================================");
        System.out.println("Version : v10.0");
        System.out.println("------------------------------------------");

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        history.add(new Reservation("SR-101", "Single Room"));
        history.add(new Reservation("DR-102", "Double Room"));

        CancellationService service = new CancellationService();

        // Perform cancellation
        service.cancel("SR-101", history, inventory);
        service.cancel("XX-999", history, inventory); // invalid case

        service.showRollbackStack();
        inventory.display();

        System.out.println("\n==========================================");
        System.out.println("Cancellation process completed.");
    }
}