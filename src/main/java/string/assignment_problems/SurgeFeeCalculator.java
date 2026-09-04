import java.util.Scanner;
public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;
    private static final double TIER1_RATE = 0.005;
    private static final double TIER2_RATE = 0.01;
    private static final double TIER3_RATE = 0.02;
    private static final int TIER1_END = 5;
    private static final int TIER2_END = 15;
    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }
    final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("orderValue cannot be negative");
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("delayMinutes cannot be negative");
        }
        if (delayMinutes == 0) {
            return 0.0;
        }
        int tier1Minutes = Math.min(delayMinutes, TIER1_END);
        int tier2Minutes = Math.max(0, Math.min(delayMinutes, TIER2_END) - TIER1_END);
        int tier3Minutes = Math.max(0, delayMinutes - TIER2_END);
        double tieredAmount = (tier1Minutes * TIER1_RATE * orderValue)
                + (tier2Minutes * TIER2_RATE * orderValue)
                + (tier3Minutes * TIER3_RATE * orderValue);
        double floorAmount = (minimumSurgePercent / 100.0) * orderValue;
        return Math.max(tieredAmount, floorAmount);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1.0);
        System.out.print("Enter orderValue: ");
        double orderValue = Double.parseDouble(sc.nextLine());
        System.out.print("Enter delayMinutes: ");
        int delayMinutes = Integer.parseInt(sc.nextLine());
        System.out.println("Rs " + calculator.calculateSurgeFee(orderValue, delayMinutes));
        sc.close();
    }
}