import java.util.Scanner;
public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;
    private static final double TIER1_RATE = 0.005;
    private static final double TIER2_RATE = 0.01;
    private static final double TIER3_RATE = 0.02;
    private static final int TIER1_END = 5;
    private static final int TIER2_END = 15;
    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }
    final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0) {
            throw new IllegalArgumentException("ticketFare cannot be negative");
        }
        if (minutesLate < 0) {
            throw new IllegalArgumentException("minutesLate cannot be negative");
        }
        if (minutesLate == 0) {
            return 0.0;
        }
        int tier1Minutes = Math.min(minutesLate, TIER1_END);
        int tier2Minutes = Math.max(0, Math.min(minutesLate, TIER2_END) - TIER1_END);
        int tier3Minutes = Math.max(0, minutesLate - TIER2_END);
        double tieredAmount = (tier1Minutes * TIER1_RATE * ticketFare)
                + (tier2Minutes * TIER2_RATE * ticketFare)
                + (tier3Minutes * TIER3_RATE * ticketFare);
        double floorAmount = (minimumPenaltyPercent / 100.0) * ticketFare;
        return Math.max(tieredAmount, floorAmount);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BoardingPenaltyCalculator calculator = new BoardingPenaltyCalculator(1.0);
        System.out.print("Enter ticketFare: ");
        double ticketFare = Double.parseDouble(sc.nextLine());
        System.out.print("Enter minutesLate: ");
        int minutesLate = Integer.parseInt(sc.nextLine());
        System.out.println("Rs " + calculator.calculatePenalty(ticketFare, minutesLate));
        sc.close();
    }
}