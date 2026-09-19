public class Room3 {

    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean available;

    public Room3(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayRoom() {

        System.out.printf(
                "%-10d %-12s ₹%-12.2f %-10s%n",
                roomNumber,
                roomType,
                pricePerNight,
                available ? "Available" : "Booked"
        );
    }
}
