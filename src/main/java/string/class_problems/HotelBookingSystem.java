import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class HotelBookingSystem {

    interface PricingStrategy {
        double calculatePrice(long days);
    }

    class StandardPricing implements PricingStrategy {
        public double calculatePrice(long days) {
            return days * 125;
        }
    }

    class DeluxePricing implements PricingStrategy {
        public double calculatePrice(long days) {
            return days * 200;
        }
    }

    class SuitePricing implements PricingStrategy {
        public double calculatePrice(long days) {
            return days * 300;
        }
    }

    class Room {
        int roomNumber;
        String category;
        PricingStrategy pricingStrategy;

        Room(int roomNumber, String category, PricingStrategy pricingStrategy) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.pricingStrategy = pricingStrategy;
        }

        double calculatePrice(LocalDate startDate, LocalDate endDate) {
            long days = ChronoUnit.DAYS.between(startDate, endDate);
            return pricingStrategy.calculatePrice(days);
        }
    }

    class Customer {
        String customerName;

        Customer(String customerName) {
            this.customerName = customerName;
        }
    }

    class Reservation {
        Customer customer;
        Room room;
        LocalDate startDate;
        LocalDate endDate;
        double totalPrice;
        String status;
        LocalDate cancellationDeadline;

        Reservation(Customer customer, Room room,
                    LocalDate startDate, LocalDate endDate) {

            this.customer = customer;
            this.room = room;
            this.startDate = startDate;
            this.endDate = endDate;
            this.totalPrice =
                    room.calculatePrice(startDate, endDate);
            this.status = "ACTIVE";
            this.cancellationDeadline =
                    startDate.minusDays(1);
        }

        boolean overlaps(LocalDate requestedStart,
                         LocalDate requestedEnd) {

            return requestedStart.isBefore(endDate)
                    && requestedEnd.isAfter(startDate);
        }

        boolean cancel(LocalDate cancellationDate) {

            if (status.equals("ACTIVE")
                    && cancellationDate.isBefore(cancellationDeadline)) {

                status = "CANCELLED";
                return true;
            }

            return false;
        }
    }

    class BookingManager {
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();

        void addRoom(Room room) {
            rooms.add(room);
        }

        Room findRoom(int roomNumber) {
            for (Room room : rooms) {
                if (room.roomNumber == roomNumber) {
                    return room;
                }
            }
            return null;
        }

        boolean isAvailable(Room room,
                            LocalDate startDate,
                            LocalDate endDate) {

            for (Reservation reservation : reservations) {

                if (reservation.room == room
                        && reservation.status.equals("ACTIVE")
                        && reservation.overlaps(startDate, endDate)) {

                    return false;
                }
            }

            return true;
        }

        Reservation bookRoom(Customer customer,
                             int roomNumber,
                             LocalDate startDate,
                             LocalDate endDate) {

            Room room = findRoom(roomNumber);

            if (room == null) {
                return null;
            }

            if (!isAvailable(room, startDate, endDate)) {

                System.out.println(
                        "Booking failed: Room " +
                                roomNumber +
                                " is not available for " +
                                startDate +
                                " to " +
                                endDate + "."
                );

                return null;
            }

            Reservation reservation =
                    new Reservation(
                            customer,
                            room,
                            startDate,
                            endDate
                    );

            reservations.add(reservation);

            System.out.println(
                    room.category +
                            " Room " +
                            room.roomNumber +
                            " booked from " +
                            startDate +
                            " to " +
                            endDate +
                            ". Total price: $" +
                            String.format("%.2f", reservation.totalPrice)
            );

            return reservation;
        }

        void cancelReservation(Reservation reservation,
                               LocalDate cancellationDate) {

            if (reservation.cancel(cancellationDate)) {

                System.out.println(
                        "Reservation for " +
                                reservation.room.category +
                                " Room " +
                                reservation.room.roomNumber +
                                " cancelled successfully."
                );

            } else {

                System.out.println(
                        "Cancellation failed."
                );
            }
        }
    }

    public static void main(String[] args) {

        HotelBookingSystem system =
                new HotelBookingSystem();

        BookingManager bookingManager =
                system.new BookingManager();

        Customer customer =
                system.new Customer("Arjun");

        Room deluxeRoom =
                system.new Room(
                        101,
                        "Deluxe",
                        system.new DeluxePricing()
                );

        Room standardRoom =
                system.new Room(
                        205,
                        "Standard",
                        system.new StandardPricing()
                );

        bookingManager.addRoom(deluxeRoom);
        bookingManager.addRoom(standardRoom);

        Reservation deluxeReservation =
                bookingManager.bookRoom(
                        customer,
                        101,
                        LocalDate.of(2024, 12, 1),
                        LocalDate.of(2024, 12, 5)
                );

        bookingManager.bookRoom(
                customer,
                205,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );

        bookingManager.bookRoom(
                customer,
                101,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );

        bookingManager.cancelReservation(
                deluxeReservation,
                LocalDate.of(2024, 11, 30)
        );
    }
}