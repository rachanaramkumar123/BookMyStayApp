/**
 * Book My Stay App
 * Use Case 7: Add-On Service Selection
 * @author Rachana
 * @version 7.0
 */

import java.util.*;

// Add-On Service Class
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() { return serviceName; }
    public double getCost() { return cost; }

    public void display() {
        System.out.println(serviceName + " - ₹" + cost);
    }
}

// Manager Class
class AddOnServiceManager {

    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Display services
    public void displayServices(String reservationId) {
        System.out.println("\nServices for Reservation ID: " + reservationId);

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            s.display();
        }
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);
        double total = 0;

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}

// Main Class
class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        BOOK MY STAY APPLICATION          ");
        System.out.println("==========================================");
        System.out.println("Version : v7.0");
        System.out.println("------------------------------------------");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "SR-101";

        // Adding services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1200));
        manager.addService(reservationId, new AddOnService("Extra Bed", 800));

        // Display services
        manager.displayServices(reservationId);

        // Total cost
        double total = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-On Cost: ₹" + total);

        System.out.println("\n==========================================");
        System.out.println("Add-on services processed successfully.");
    }
}