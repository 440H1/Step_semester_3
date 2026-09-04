class BusTicketAccount {
    protected final String bookingId;
    protected final double ticketFare;
    protected static int totalAccountsCreated;
    protected static final double FLAT_RATE_PER_MINUTE = 0.01;
    static {
        totalAccountsCreated = 0;
    }
    public BusTicketAccount(String bookingId, double ticketFare) {
        if (ticketFare < 0) {
            throw new IllegalArgumentException("ticketFare cannot be negative");
        }
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
        totalAccountsCreated++;
    }
    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }
    final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException("minutesLate cannot be negative");
        }
        return minutesLate * FLAT_RATE_PER_MINUTE * ticketFare;
    }
    String getBookingId() {
        return bookingId;
    }
}
class SleeperBusTicketAccount extends BusTicketAccount {
    public SleeperBusTicketAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
    public SleeperBusTicketAccount(String bookingId) {
        super(bookingId);
    }
}
public class FleetReconciliationEngine {
    static int processedCount = 0;
    static int nullSkippedCount = 0;
    static int sleeperCount = 0;
    static int regularCount = 0;
    static double grandTotalPenalties = 0.0;
    static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            nullSkippedCount++;
            return;
        }
        double penalty = account.calculatePenalty(minutesLate);
        if (account instanceof SleeperBusTicketAccount) {
            sleeperCount++;
        } else {
            regularCount++;
        }
        grandTotalPenalties += penalty;
        processedCount++;
    }
    static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        int batchSize = Math.min(accounts.length, Math.min(amounts.length, minutesLateArray.length));

        for (int i = 0; i < batchSize; i++) {
            processAccount(accounts[i], amounts[i], minutesLateArray[i]);
        }
        System.out.println(processedCount + " processed | " + nullSkippedCount + " null skipped | "
                + sleeperCount + " sleeper | " + regularCount + " regular | grand total penalties = "
                + grandTotalPenalties);
    }
    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
                new SleeperBusTicketAccount("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};
        processBatch(accounts, amounts, minutesLateArray);
    }
}