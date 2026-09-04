class DeliveryAccount {
    protected final String studentId;
    protected final double orderValue;
    protected static int totalAccountsCreated;
    protected static final double FLAT_RATE_PER_MINUTE = 0.01;
    static {
        totalAccountsCreated = 0;
    }
    public DeliveryAccount(String studentId, double orderValue) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("orderValue cannot be negative");
        }
        this.studentId = studentId;
        this.orderValue = orderValue;
        totalAccountsCreated++;
    }
    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }
    final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("delayMinutes cannot be negative");
        }
        return delayMinutes * FLAT_RATE_PER_MINUTE * orderValue;
    }
    String getStudentId() {
        return studentId;
    }
}
class PremiumDeliveryAccount extends DeliveryAccount {
    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
    public PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }
}
public class MultiKitchenReconciliationEngine {
    static int processedCount = 0;
    static int nullSkippedCount = 0;
    static int premiumCount = 0;
    static int regularCount = 0;
    static double grandTotalSurgeFees = 0.0;
    static void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            nullSkippedCount++;
            return;
        }
        double surgeFee = account.calculateSurgeFee(delayMinutes);
        if (account instanceof PremiumDeliveryAccount) {
            premiumCount++;
        } else {
            regularCount++;
        }
        grandTotalSurgeFees += surgeFee;
        processedCount++;
    }
    static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        int batchSize = Math.min(accounts.length, Math.min(amounts.length, delayMinutesArray.length));
        for (int i = 0; i < batchSize; i++) {
            processAccount(accounts[i], amounts[i], delayMinutesArray[i]);
        }
        System.out.println(processedCount + " processed | " + nullSkippedCount + " null skipped | "
                + premiumCount + " premium | " + regularCount + " regular | grand total surge fees = "
                + grandTotalSurgeFees);
    }
    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
                new PremiumDeliveryAccount("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};
        processBatch(accounts, amounts, delayMinutesArray);
    }
}