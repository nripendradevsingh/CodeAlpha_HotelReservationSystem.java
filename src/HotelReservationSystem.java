import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HotelReservationSystem {

    static Scanner sc = new Scanner(System.in);

    static Hotel hotel = new Hotel();

    static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "       HOTEL RESERVATION SYSTEM"
        );

        System.out.println(
                "=============================================="
        );

        int choice;

        do {

            displayMenu();

            System.out.print(
                    "\nEnter your choice: "
            );

            choice = sc.nextInt();

            sc.nextLine();

            switch (choice) {

                case 1:
                    hotel.displayAllRooms();
                    break;

                case 2:
                    searchRooms();
                    break;

                case 3:
                    bookRoom();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    viewBooking();
                    break;

                case 6:
                    hotel.displayAllReservations();
                    break;

                case 7:
                    System.out.println(
                            "\nThank you for using "
                                    + "Hotel Reservation System!"
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice!"
                    );
            }

        } while (choice != 7);

        sc.close();
    }

    static void displayMenu() {

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "                    MENU"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "1. View All Rooms"
        );

        System.out.println(
                "2. Search Available Rooms"
        );

        System.out.println(
                "3. Book a Room"
        );

        System.out.println(
                "4. Cancel Reservation"
        );

        System.out.println(
                "5. View Booking Details"
        );

        System.out.println(
                "6. View All Reservations"
        );

        System.out.println(
                "7. Exit"
        );
    }

    static void searchRooms() {

        System.out.println(
                "\nRoom Categories:"
        );

        System.out.println(
                "1. Standard"
        );

        System.out.println(
                "2. Deluxe"
        );

        System.out.println(
                "3. Suite"
        );

        System.out.print(
                "Enter room type: "
        );

        String type = sc.nextLine();

        hotel.searchAvailableRooms(type);
    }

    static void bookRoom() {

        System.out.println(
                "\n========== ROOM BOOKING =========="
        );

        System.out.print(
                "Enter customer name: "
        );

        String name = sc.nextLine();

        System.out.print(
                "Enter phone number: "
        );

        String phone = sc.nextLine();

        System.out.print(
                "Enter email: "
        );

        String email = sc.nextLine();

        Customer customer =
                new Customer(
                        name,
                        phone,
                        email
                );

        hotel.displayAllRooms();

        System.out.print(
                "\nEnter room number: "
        );

        int roomNumber = sc.nextInt();

        sc.nextLine();

        System.out.print(
                "Enter Check-In date (dd-MM-yyyy): "
        );

        String checkInInput = sc.nextLine();

        System.out.print(
                "Enter Check-Out date (dd-MM-yyyy): "
        );

        String checkOutInput = sc.nextLine();

        try {

            LocalDate checkIn =
                    LocalDate.parse(
                            checkInInput,
                            formatter
                    );

            LocalDate checkOut =
                    LocalDate.parse(
                            checkOutInput,
                            formatter
                    );

            if (!checkOut.isAfter(checkIn)) {

                System.out.println(
                        "Check-Out must be after Check-In."
                );

                return;
            }

            Reservation reservation =
                    hotel.createReservation(
                            customer,
                            roomNumber,
                            checkIn,
                            checkOut
                    );

            if (reservation == null) {

                System.out.println(
                        "\nRoom is not available "
                                + "or room number is invalid."
                );

                return;
            }

            reservation.displayReservation();

            paymentSimulation();

            System.out.println(
                    "\nBooking completed successfully!"
            );

        } catch (DateTimeParseException e) {

            System.out.println(
                    "Invalid date format!"
            );

            System.out.println(
                    "Please use dd-MM-yyyy."
            );
        }
    }

    static void paymentSimulation() {

        System.out.println(
                "\n================================="
        );

        System.out.println(
                "        PAYMENT SIMULATION"
        );

        System.out.println(
                "================================="
        );

        System.out.println(
                "1. UPI"
        );

        System.out.println(
                "2. Debit/Credit Card"
        );

        System.out.println(
                "3. Cash"
        );

        System.out.print(
                "Select payment method: "
        );

        int paymentMethod = sc.nextInt();

        switch (paymentMethod) {

            case 1:
                System.out.println(
                        "Payment processed through UPI."
                );
                break;

            case 2:
                System.out.println(
                        "Payment processed through Card."
                );
                break;

            case 3:
                System.out.println(
                        "Cash payment selected."
                );
                break;

            default:
                System.out.println(
                        "Invalid payment option."
                );
        }

        System.out.println(
                "Payment Status: SUCCESS"
        );
    }

    static void cancelBooking() {

        System.out.println(
                "\n========== CANCEL BOOKING =========="
        );

        System.out.print(
                "Enter Booking ID: "
        );

        String bookingId = sc.nextLine();

        boolean cancelled =
                hotel.cancelReservation(
                        bookingId
                );

        if (cancelled) {

            System.out.println(
                    "Reservation cancelled successfully."
            );

        } else {

            System.out.println(
                    "Booking not found or already cancelled."
            );
        }
    }

    static void viewBooking() {

        System.out.println(
                "\n========== VIEW BOOKING =========="
        );

        System.out.print(
                "Enter Booking ID: "
        );

        String bookingId = sc.nextLine();

        Reservation reservation =
                hotel.findReservation(
                        bookingId
                );

        if (reservation == null) {

            System.out.println(
                    "Booking not found."
            );

        } else {

            reservation.displayReservation();
        }
    }
}