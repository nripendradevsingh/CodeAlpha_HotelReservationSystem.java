import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private String bookingId;
    private Customer customer;
    private Room3 room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double totalAmount;
    private boolean active;

    public Reservation(
            String bookingId,
            Customer customer,
            Room3 room,
            LocalDate checkIn,
            LocalDate checkOut) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        long nights =
                java.time.temporal.ChronoUnit.DAYS.between(
                        checkIn,
                        checkOut
                );

        this.totalAmount =
                nights * room.getPricePerNight();

        this.active = true;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Room3 getRoom() {
        return room;
    }

    public boolean isActive() {
        return active;
    }

    public void cancelReservation() {
        active = false;
        room.setAvailable(true);
    }

    public void displayReservation() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println(
                "\n=========================================="
        );

        System.out.println(
                "          BOOKING DETAILS"
        );

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "Booking ID    : " + bookingId
        );

        customer.displayCustomer();

        System.out.println(
                "Room Number   : " + room.getRoomNumber()
        );

        System.out.println(
                "Room Type     : " + room.getRoomType()
        );

        System.out.printf(
                "Price/Night   : ₹%.2f%n",
                room.getPricePerNight()
        );

        System.out.println(
                "Check-In      : " +
                        checkIn.format(formatter)
        );

        System.out.println(
                "Check-Out     : " +
                        checkOut.format(formatter)
        );

        long nights =
                java.time.temporal.ChronoUnit.DAYS.between(
                        checkIn,
                        checkOut
                );

        System.out.println(
                "Total Nights  : " + nights
        );

        System.out.printf(
                "Total Amount  : ₹%.2f%n",
                totalAmount
        );

        System.out.println(
                "Status        : " +
                        (active ? "CONFIRMED" : "CANCELLED")
        );

        System.out.println(
                "=========================================="
        );
    }

    public String getFileData() {

        return bookingId + "|" +
                customer.getName() + "|" +
                customer.getPhone() + "|" +
                customer.getEmail() + "|" +
                room.getRoomNumber() + "|" +
                room.getRoomType() + "|" +
                checkIn + "|" +
                checkOut + "|" +
                totalAmount + "|" +
                (active ? "CONFIRMED" : "CANCELLED");
    }
}
