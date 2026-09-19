import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Hotel {

    private ArrayList<Room3> rooms;
    private ArrayList<Reservation> reservations;

    private int bookingCounter = 1001;

    public Hotel() {

        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        initializeRooms();
    }

    private void initializeRooms() {

        // Standard Rooms
        rooms.add(new Room3(101, "Standard", 1500));
        rooms.add(new Room3(102, "Standard", 1500));
        rooms.add(new Room3(103, "Standard", 1500));
        rooms.add(new Room3(104, "Standard", 1500));

        // Deluxe Rooms
        rooms.add(new Room3(201, "Deluxe", 2500));
        rooms.add(new Room3(202, "Deluxe", 2500));
        rooms.add(new Room3(203, "Deluxe", 2500));

        // Suite Rooms
        rooms.add(new Room3(301, "Suite", 4000));
        rooms.add(new Room3(302, "Suite", 4000));
        rooms.add(new Room3(303, "Suite", 4000));
    }

    public void displayAllRooms() {

        System.out.println(
                "\n================================================"
        );

        System.out.println(
                "                 HOTEL ROOMS"
        );

        System.out.println(
                "================================================"
        );

        System.out.printf(
                "%-10s %-12s %-15s %-10s%n",
                "Room No.",
                "Type",
                "Price/Night",
                "Status"
        );

        System.out.println(
                "------------------------------------------------"
        );

        for (Room3 room : rooms) {
            room.displayRoom();
        }
    }

    public void searchAvailableRooms(String type) {

        boolean found = false;

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "        AVAILABLE " + type.toUpperCase()
                        + " ROOMS"
        );

        System.out.println(
                "=============================================="
        );

        for (Room3 room : rooms) {

            if (room.isAvailable()
                    && room.getRoomType()
                    .equalsIgnoreCase(type)) {

                room.displayRoom();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No available rooms found."
            );
        }
    }

    public Room3 findAvailableRoom(int roomNumber) {

        for (Room3 room : rooms) {

            if (room.getRoomNumber() == roomNumber
                    && room.isAvailable()) {

                return room;
            }
        }

        return null;
    }

    public Reservation createReservation(
            Customer customer,
            int roomNumber,
            java.time.LocalDate checkIn,
            java.time.LocalDate checkOut) {

        Room3 room = findAvailableRoom(roomNumber);

        if (room == null) {
            return null;
        }

        Reservation reservation =
                new Reservation(
                        "BK" + bookingCounter++,
                        customer,
                        room,
                        checkIn,
                        checkOut
                );

        room.setAvailable(false);

        reservations.add(reservation);

        saveReservation(reservation);

        return reservation;
    }

    public Reservation findReservation(
            String bookingId) {

        for (Reservation reservation :
                reservations) {

            if (reservation.getBookingId()
                    .equalsIgnoreCase(bookingId)
                    && reservation.isActive()) {

                return reservation;
            }
        }

        return null;
    }

    public boolean cancelReservation(
            String bookingId) {

        Reservation reservation =
                findReservation(bookingId);

        if (reservation == null) {
            return false;
        }

        reservation.cancelReservation();

        saveReservation(reservation);

        return true;
    }

    public void displayAllReservations() {

        if (reservations.isEmpty()) {

            System.out.println(
                    "\nNo reservations available."
            );

            return;
        }

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "            ALL RESERVATIONS"
        );

        System.out.println(
                "=============================================="
        );

        for (Reservation reservation :
                reservations) {

            reservation.displayReservation();
        }
    }

    private void saveReservation(
            Reservation reservation) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "bookings.txt",
                            true
                    );

            writer.write(
                    reservation.getFileData()
                            + System.lineSeparator()
            );

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving booking data."
            );
        }
    }
}
