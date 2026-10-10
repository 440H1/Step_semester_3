import java.util.HashMap;
import java.util.Map;

public class NetBalancePeriodCounter {
    public static int countPeriods(int[] transactions, int k) {
        Map<Long, Integer> prefixCounts = new HashMap<>();
        prefixCounts.put(0L, 1);

        long currentSum = 0;
        int periodCount = 0;

        for (int x : transactions) {
            currentSum += x;

            if (prefixCounts.containsKey(currentSum - k)) {
                periodCount += prefixCounts.get(currentSum - k);
            }

            prefixCounts.put(currentSum, prefixCounts.getOrDefault(currentSum, 0) + 1);
        }

        return periodCount;
    }

    public static void main(String[] args) {
        int[] transactions1 = {3, 4, -7, 1, 3, 3, 1, -4};
        int k1 = 7;
        System.out.println("Sample 1 Output: " + countPeriods(transactions1, k1));

        int[] transactions2 = {1, 2, 3};
        int k2 = 10;
        System.out.println("Sample 2 Output: " + countPeriods(transactions2, k2));
    }
}