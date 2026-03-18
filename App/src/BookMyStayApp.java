/**
 * Book My Stay App
 * Use Case 12: Data Persistence & System Recovery
 * @author Rachana
 * @version 12.0
 */

import java.io.*;
import java.util.*;

// Reservation (Serializable)
class Reservation implements Serializable {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() { return reservationId; }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    public void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}

// Inventory (Serializable)
class RoomInventory implements Serializable {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}

// Wrapper Class for Persistence
class SystemState implements Serializable {
    List<Reservation> bookings;
    RoomInventory inventory;

    public SystemState(List<Reservation> bookings, RoomInventory inventory) {
        this.bookings = bookings;
        this.inventory = inventory;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    // Save state
    public static void save(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("\nState saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state
    public static SystemState load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("State loaded successfully.");
            return (SystemState) ois.readObject();
        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return null;
        }
    }
}

// Main
class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        BOOK MY STAY APPLICATION          ");
        System.out.println("==========================================");
        System.out.println("Version : v12.0");
        System.out.println("------------------------------------------");

        // Try loading previous state
        SystemState state = PersistenceService.load();

        List<Reservation> bookings;
        RoomInventory inventory;

        if (state != null) {
            bookings = state.bookings;
            inventory = state.inventory;
        } else {
            bookings = new ArrayList<>();
            inventory = new RoomInventory();

            // Simulate new bookings
            bookings.add(new Reservation("SR-101", "Alice", "Single Room"));
            bookings.add(new Reservation("DR-102", "Bob", "Double Room"));
        }

        // Display current state
        System.out.println("\n--- Booking History ---");
        for (Reservation r : bookings) {
            r.display();
        }

        inventory.display();

        // Save state before exit
        PersistenceService.save(new SystemState(bookings, inventory));

        System.out.println("\n==========================================");
        System.out.println("System ready with persisted state.");
    }
}