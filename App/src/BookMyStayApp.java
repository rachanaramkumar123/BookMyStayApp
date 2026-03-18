/**
 * Book My Stay App
 * Use Case 8: Booking History & Reporting
 * @author Rachana
 * @version 8.0
 */

import java.util.*;

// Reservation Class
class Reservation {
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
        System.out.println("ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

// Booking History
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Report Service
class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {
        System.out.println("\n--- Booking History ---\n");

        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : reservations) {
            r.display();
        }
    }

    public void generateSummary(List<Reservation> reservations) {
        System.out.println("\n--- Booking Summary ---\n");

        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : reservations) {
            summary.put(r.getRoomType(),
                    summary.getOrDefault(r.getRoomType(), 0) + 1);
        }

        for (String type : summary.keySet()) {
            System.out.println(type + " Bookings: " + summary.get(type));
        }

        System.out.println("Total Bookings: " + reservations.size());
    }
}

// Main Class
class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        BOOK MY STAY APPLICATION          ");
        System.out.println("==========================================");
        System.out.println("Version : v8.0");
        System.out.println("------------------------------------------");

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        history.addReservation(new Reservation("SR-101", "Alice", "Single Room"));
        history.addReservation(new Reservation("DR-102", "Bob", "Double Room"));
        history.addReservation(new Reservation("SR-103", "Charlie", "Single Room"));

        // Reporting
        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history.getAllReservations());
        reportService.generateSummary(history.getAllReservations());

        System.out.println("\n==========================================");
        System.out.println("Reporting completed successfully.");
    }
}