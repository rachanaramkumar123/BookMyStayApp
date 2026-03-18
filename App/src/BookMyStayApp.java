/**
 * Book My Stay App
 * Use Case 2: Basic Room Types & Static Availability
 * @author Rachana
 * @version 2.0
 */

abstract class Room {

    private String roomType;
    private int numberOfBeds;
    private double price;

    public Room(String roomType, int numberOfBeds, double price) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 2000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getNumberOfBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 3500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getNumberOfBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 6000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getNumberOfBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}

class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("        BOOK MY STAY APPLICATION          ");
        System.out.println("==========================================");
        System.out.println("Version : v2.0");
        System.out.println("------------------------------------------");

        Room single = new SingleRoom();
        Room doubleroom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("Single Room Details:");
        single.displayDetails();
        System.out.println("Available Rooms: " + singleAvailable);

        System.out.println("------------------------------------------");

        System.out.println("Double Room Details:");
        doubleroom.displayDetails();
        System.out.println("Available Rooms: " + doubleAvailable);

        System.out.println("------------------------------------------");

        System.out.println("Suite Room Details:");
        suite.displayDetails();
        System.out.println("Available Rooms: " + suiteAvailable);

        System.out.println("==========================================");
        System.out.println("Application execution completed.");
    }
}