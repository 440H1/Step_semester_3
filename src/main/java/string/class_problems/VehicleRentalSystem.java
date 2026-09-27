import java.util.*;

public class VehicleRentalSystem {

    abstract class Vehicle {
        String vehicleName;
        boolean available = true;

        Vehicle(String vehicleName) {
            this.vehicleName = vehicleName;
        }

        abstract double calculateCharge(int days);

        void rent() {
            available = false;
        }

        void returnVehicle() {
            available = true;
        }
    }

    class StandardCar extends Vehicle {

        StandardCar(String vehicleName) {
            super(vehicleName);
        }

        double calculateCharge(int days) {
            return days * 50;
        }
    }

    class LuxuryCar extends Vehicle {

        LuxuryCar(String vehicleName) {
            super(vehicleName);
        }

        double calculateCharge(int days) {
            return days * 100;
        }
    }

    class SUV extends Vehicle {

        SUV(String vehicleName) {
            super(vehicleName);
        }

        double calculateCharge(int days) {
            return days * 80;
        }
    }

    class Customer {
        String customerName;

        Customer(String customerName) {
            this.customerName = customerName;
        }
    }

    class Rental {
        Customer customer;
        Vehicle vehicle;
        int days;
        double totalCharge;

        Rental(Customer customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
            this.totalCharge = vehicle.calculateCharge(days);
        }
    }

    class RentalService {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<Rental> rentals = new ArrayList<>();

        void addVehicle(Vehicle vehicle) {
            vehicles.add(vehicle);
        }

        void rentVehicle(Customer customer, String vehicleName, int days) {

            for (Vehicle vehicle : vehicles) {

                if (vehicle.vehicleName.equals(vehicleName)) {

                    if (!vehicle.available) {
                        System.out.println(
                                vehicleName + " is currently unavailable."
                        );
                        return;
                    }

                    vehicle.rent();

                    Rental rental =
                            new Rental(customer, vehicle, days);

                    rentals.add(rental);

                    System.out.println(
                            vehicleName +
                                    " rented for " +
                                    days +
                                    " days. Total charge: $" +
                                    String.format("%.2f", rental.totalCharge)
                    );

                    return;
                }
            }
        }

        void returnVehicle(String vehicleName) {

            for (Rental rental : rentals) {

                if (rental.vehicle.vehicleName.equals(vehicleName)
                        && !rental.vehicle.available) {

                    rental.vehicle.returnVehicle();

                    System.out.println(
                            vehicleName +
                                    " returned. Now available."
                    );

                    return;
                }
            }
        }
    }

    public static void main(String[] args) {

        VehicleRentalSystem system =
                new VehicleRentalSystem();

        Customer customer =
                system.new Customer("Arjun");

        Vehicle luxuryCar =
                system.new LuxuryCar("Luxury Car A");

        Vehicle standardCar =
                system.new StandardCar("Standard Car B");

        RentalService rentalService =
                system.new RentalService();

        rentalService.addVehicle(luxuryCar);
        rentalService.addVehicle(standardCar);

        rentalService.rentVehicle(
                customer,
                "Luxury Car A",
                3
        );

        rentalService.rentVehicle(
                customer,
                "Standard Car B",
                5
        );

        rentalService.returnVehicle(
                "Luxury Car A"
        );
    }
}