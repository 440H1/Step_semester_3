
import java.util.Scanner;
import java.text.DecimalFormat;

public class RideFareEngine {

    static abstract class Ride {
        double distance;

        Ride(double distance) {
            this.distance = distance;
        }

        abstract double calculateFare();
    }

    static class AutoRide extends Ride {

        AutoRide(double distance) {
            super(distance);
        }

        double calculateFare() {
            return 30 + 15 * distance;
        }
    }

    static class CabRide extends Ride {
        double surge;

        CabRide(double distance, double surge) {
            super(distance);
            this.surge = surge;
        }

        double calculateFare() {
            return (50 + 20 * distance) * surge;
        }
    }

    static class BikeRide extends Ride {
        int waitingMinutes;

        BikeRide(double distance, int waitingMinutes) {
            super(distance);
            this.waitingMinutes = waitingMinutes;
        }

        double calculateFare() {
            return 20 + 10 * distance + 2 * waitingMinutes;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.##");

        int n = sc.nextInt();

        Ride[] rides = new Ride[n];

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double distance = sc.nextDouble();

            if (type.equalsIgnoreCase("Auto")) {
                rides[i] = new AutoRide(distance);
            }
            else if (type.equalsIgnoreCase("Cab")) {
                double surge = sc.nextDouble();
                rides[i] = new CabRide(distance, surge);
            }
            else if (type.equalsIgnoreCase("Bike")) {
                int waiting = sc.nextInt();
                rides[i] = new BikeRide(distance, waiting);
            }
        }

        double grandTotal = 0;

        for (int i = 0; i < n; i++) {
            double fare = rides[i].calculateFare();

            System.out.println("Fare: " + df.format(fare));

            grandTotal += fare;
        }

        System.out.println("Total: " + df.format(grandTotal));

        sc.close();
    }
}
