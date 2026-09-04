import java.util.Arrays;

public class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;
    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }
    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }
    public FareSplitter(String tripId) {
        this(tripId, 0.0);
    }
    double[] fareBreakdown() {
        long totalPaisa = Math.round(totalFare * 100);
        long basePaisa = totalPaisa / passengerCount;
        long remainder = totalPaisa % passengerCount;
        double[] shares = new double[passengerCount];
        for (int i = 0; i < passengerCount; i++) {
            shares[i] = basePaisa / 100.0;
        }
        shares[passengerCount - 1] += remainder / 100.0;
        return shares;
    }
    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
    public static void main(String[] args) {
        FareSplitter full = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(full.fareBreakdown()));

        FareSplitter provisional = new FareSplitter("TRIP003");
        System.out.println(Arrays.toString(provisional.fareBreakdown()));
    }
}