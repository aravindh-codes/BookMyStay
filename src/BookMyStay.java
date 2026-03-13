import java.util.*;

/**
 * ================================================
 * CLASS - Reservation
 * ================================================
 *
 * Represents a guest booking request.
 *
 * @version 6.0
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/**
 * ====================================================
 * CLASS - BookingRequestQueue
 * ====================================================
 *
 * Handles booking requests in FIFO order.
 *
 * @version 6.0
 */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}

/**
 * ====================================================
 * CLASS - RoomInventory
 * ====================================================
 *
 * Stores room availability.
 *
 * @version 6.0
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 2);
        roomAvailability.put("Suite", 1);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        roomAvailability.put(roomType, roomAvailability.get(roomType) - 1);
    }
}

/**
 * ====================================================
 * CLASS - RoomAllocationService
 * ====================================================
 *
 * Confirms booking requests and assigns rooms safely.
 *
 * @version 6.0
 */
class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();

        if (inventory.getAvailability(roomType) <= 0) {
            System.out.println("No rooms available for " + roomType +
                    " (Guest: " + reservation.getGuestName() + ")");
            return;
        }

        String roomId = generateRoomId(roomType);

        allocatedRoomIds.add(roomId);

        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        inventory.decrementRoom(roomType);

        System.out.println("Reservation Confirmed -> Guest: "
                + reservation.getGuestName()
                + ", Room Type: " + roomType
                + ", Room ID: " + roomId);
    }

    private String generateRoomId(String roomType) {

        String roomId;

        do {
            roomId = roomType.substring(0, 2).toUpperCase()
                    + (int)(Math.random() * 1000);
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }
}

/**
 * ====================================================
 * MAIN CLASS - BookMyStay
 * ====================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Demonstrates safe booking confirmation
 * and prevention of double booking.
 *
 * @version 6.0
 */
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Room Allocation System\n");

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocator = new RoomAllocationService();

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Double"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));
        queue.addRequest(new Reservation("Ravi", "Single"));

        while (queue.hasPendingRequests()) {
            Reservation next = queue.getNextRequest();
            allocator.allocateRoom(next, inventory);
        }

        System.out.println("\nRemaining Inventory: " +
                inventory.getRoomAvailability());
    }
}