import java.util.HashMap;
import java.util.Map;

/**
 * ================================================================
 * ABSTRACT CLASS - Room
 * ================================================================
 *
 * Represents a generic hotel room.
 *
 * @version 3.1
 */
abstract class Room {

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Room Size: " + squareFeet + " sq ft");
        System.out.println("Price per night: ₹" + pricePerNight);
    }
}

/**
 * ================================================================
 * CLASS - SingleRoom
 * ================================================================
 *
 * Represents a single room.
 *
 * @version 3.1
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/**
 * ================================================================
 * CLASS - DoubleRoom
 * ================================================================
 *
 * Represents a double room.
 *
 * @version 3.1
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

/**
 * ================================================================
 * CLASS - SuiteRoom
 * ================================================================
 *
 * Represents a suite room.
 *
 * @version 3.0
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 650, 5000.0);
    }
}

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 *
 * Manages centralized room availability using HashMap.
 *
 * @version 3.1
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 10);
        roomAvailability.put("Double", 5);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int newCount) {

        if (roomAvailability.containsKey(roomType)) {
            roomAvailability.put(roomType, newCount);
        } else {
            System.out.println("Room type not found.");
        }
    }
}

/**
 * ================================================================
 * MAIN CLASS - BookMyStay
 * ================================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * @version 3.1
 */
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("           Version 3.1           ");
        System.out.println("=================================\n");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        System.out.println("Room Details:\n");

        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println();

        System.out.println("Double Room:");
        doubleRoom.displayRoomDetails();
        System.out.println();

        System.out.println("Suite Room:");
        suite.displayRoomDetails();
        System.out.println();

        System.out.println("Initial Room Availability:");
        System.out.println(inventory.getRoomAvailability());

        inventory.updateAvailability("Double", 8);

        System.out.println("\nUpdated Room Availability:");
        System.out.println(inventory.getRoomAvailability());
    }
}