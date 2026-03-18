/**
 * Book My Stay App
 * Use Case 4: Room Search & Availability Check
 * @author Rachana
 * @version 4.0
 */

import java.util.HashMap;
import java.util.Map;

// Abstract Room
abstract class Room {
    private String roomType;
    private int beds;
    private double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public String getRoomType() { return roomType; }
    public int getBeds() { return beds; }
    public double getPrice() { return price; }

    public abstract void display();
}

// Concrete Rooms
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 2000); }
    public void display() {
        System.out.println(getRoomType() + " | Beds: " + getBeds() + " | Price: ₹" + getPrice());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 3500); }
    public void display() {
        System.out.println(getRoomType() + " | Beds: " + getBeds() + " | Price: ₹" + getPrice());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 6000); }
    public void display() {
        System.out.println(getRoomType() + " | Beds: " + getBeds() + " | Price: ₹" + getPrice());
    }
}

// Inventory (Read-Only Access used here)
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public Map<String, Integer> getAll() {
        return inventory;
    }
}

// Search Service (Read-only)
class SearchService {

    public static void searchAvailableRooms(RoomInventory inventory) {

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        System.out.println("Available Rooms:\n");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {
                room.display();
                System.out.println("Available: " + available);
                System.out.println("---------------------------");
            }
        }
    }
}

// Main
class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        BOOK MY STAY APPLICATION          ");
        System.out.println("==========================================");
        System.out.println("Version : v4.0");
        System.out.println("------------------------------------------");

        RoomInventory inventory = new RoomInventory();

        SearchService.searchAvailableRooms(inventory);

        System.out.println("==========================================");
        System.out.println("Search completed. No changes made to inventory.");
    }
}