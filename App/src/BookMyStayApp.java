/**
 * Book My Stay App
 * Use Case 9: Error Handling & Validation
 * @author Rachana
 * @version 9.0
 */

import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation Class
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

// Inventory
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public boolean isValidRoomType(String type) {
        return inventory.containsKey(type);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void reduceRoom(String type) throws InvalidBookingException {
        int count = getAvailability(type);

        if (count <= 0) {
            throw new InvalidBookingException("No rooms available for " + type);
        }

        inventory.put(type, count - 1);
    }
}

// Validator
class BookingValidator {

    public static void validate(Reservation r, RoomInventory inventory)
            throws InvalidBookingException {

        if (r.getGuestName() == null || r.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (!inventory.isValidRoomType(r.getRoomType())) {
            throw new InvalidBookingException("Invalid room type: " + r.getRoomType());
        }

        if (inventory.getAvailability(r.getRoomType()) <= 0) {
            throw new InvalidBookingException("No availability for " + r.getRoomType());
        }
    }
}

// Booking Service
class BookingService {

    public void processBooking(Reservation r, RoomInventory inventory) {

        try {
            BookingValidator.validate(r, inventory);

            inventory.reduceRoom(r.getRoomType());

            System.out.println("Booking Confirmed for " + r.getGuestName() +
                    " | Room: " + r.getRoomType());

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}

// Main Class
class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        BOOK MY STAY APPLICATION          ");
        System.out.println("==========================================");
        System.out.println("Version : v9.0");
        System.out.println("------------------------------------------");

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService();

        // Test cases
        service.processBooking(new Reservation("Alice", "Single Room"), inventory);
        service.processBooking(new Reservation("", "Double Room"), inventory); // invalid name
        service.processBooking(new Reservation("Bob", "Suite Room"), inventory); // invalid type
        service.processBooking(new Reservation("Charlie", "Single Room"), inventory); // no availability

        System.out.println("\n==========================================");
        System.out.println("Validation completed.");
    }
}