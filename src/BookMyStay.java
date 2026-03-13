import java.util.LinkedList;
import java.util.Queue;

/**
 * ================================================
 * CLASS - Reservation
 * ================================================
 *
 * Use Case 5: Booking Request (FIFO)
 *
 * Represents a guest's booking request.
 *
 * @version 5.0
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
 * ================================================================
 * CLASS - BookingRequestQueue
 * ================================================================
 *
 * Manages booking requests using FIFO Queue.
 *
 * @version 5.0
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
 * ============================================================
 * MAIN CLASS - BookMyStay
 * ============================================================
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Demonstrates fair booking request handling
 * using a FIFO queue.
 *
 * @version 5.0
 */
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Booking Request Queue");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        while (bookingQueue.hasPendingRequests()) {
            Reservation next = bookingQueue.getNextRequest();

            System.out.println(
                    "Processing booking for Guest: "
                            + next.getGuestName()
                            + ", Room Type: "
                            + next.getRoomType()
            );
        }
    }
}