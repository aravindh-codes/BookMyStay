import java.util.HashMap;
import java.util.Map;

/**
 * ================================================================
 * CLASS - Room
 * ================================================================
 *
 * Domain model representing a room type.
 * Provides descriptive information used by search services.
 *
 * @version 4.0
 */
class Room {

    private String roomType;
    private int numberOfBeds;
    private int squareFeet;
    private double pricePerNight;

    public Room(String roomType, int numberOfBeds, int squareFeet, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Beds: " + numberOfBeds +
                "\nSize: " + squareFeet + " sqft" +
                "\nPrice per night: " + pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }
}

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 *
 * Centralized storage for room availability.
 * Acts as the single source of truth for inventory.
 *
 * @version 4.0
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}

/**
 * ================================================================
 * CLASS - RoomSearchService
 * ================================================================
 *
 * Provides read-only search functionality for guests.
 * Retrieves availability from inventory and details from Room objects.
 *
 * @version 4.0
 */
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        if (availability.get("Single") > 0) {
            System.out.println("Single Room:");
            System.out.println(singleRoom);
            System.out.println("Available: " + availability.get("Single") + "\n");
        }

        if (availability.get("Double") > 0) {
            System.out.println("Double Room:");
            System.out.println(doubleRoom);
            System.out.println("Available: " + availability.get("Double") + "\n");
        }

        if (availability.get("Suite") > 0) {
            System.out.println("Suite Room:");
            System.out.println(suiteRoom);
            System.out.println("Available: " + availability.get("Suite"));
        }
    }
}

/**
 * ================================================================
 * MAIN CLASS - BookMyStay
 * ================================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates how guests can view available rooms
 * without modifying inventory data.
 *
 * @version 4.0
 */
public class BookMyStay {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room singleRoom = new Room("Single", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double", 2, 400, 2500.0);
        Room suiteRoom = new Room("Suite", 3, 750, 5000.0);

        RoomSearchService searchService = new RoomSearchService();

        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );
    }
}