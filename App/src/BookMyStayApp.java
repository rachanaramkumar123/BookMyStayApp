/**
 * Book My Stay App
 * Use Case 3: Centralized Room Inventory Management
 * @author Rachana
 * @version 3.0
 */

import java.util.HashMap;
import java.util.Map;

// Inventory Class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor - initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        } else {
            System.out.println("Room type not found.");
        }
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("Current Room Availability:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Main Class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        BOOK MY STAY APPLICATION          ");
        System.out.println("==========================================");
        System.out.println("Version : v3.0");
        System.out.println("------------------------------------------");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        System.out.println("------------------------------------------");

        // Update availability
        System.out.println("Updating Single Room availability to 4...");
        inventory.updateAvailability("Single Room", 4);

        System.out.println("------------------------------------------");

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("==========================================");
        System.out.println("Application execution completed.");
    }
}