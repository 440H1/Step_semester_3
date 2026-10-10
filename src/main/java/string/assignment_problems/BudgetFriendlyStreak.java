import java.util.Arrays;

public class BudgetFriendlyStreak {
    public static int[] longestStreak(int[] costs, int budget) {
        int maxLen = 0;
        int bestStart = -1;

        int left = 0;
        long currentSum = 0;

        for (int right = 0; right < costs.length; right++) {
            currentSum += costs[right];

            while (currentSum > budget && left <= right) {
                currentSum -= costs[left];
                left++;
            }

            if (currentSum <= budget) {
                int windowLen = right - left + 1;
                if (windowLen > maxLen) {
                    maxLen = windowLen;
                    bestStart = left;
                }
            }
        }

        if (maxLen == 0) {
            return new int[]{0, -1};
        }

        return new int[]{maxLen, bestStart};
    }

    public static void main(String[] args) {
        int[] costs1 = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        int budget1 = 8;
        System.out.println("Sample 1 Output: " + Arrays.toString(longestStreak(costs1, budget1)));

        int[] costs2 = {9, 10};
        int budget2 = 8;
        System.out.println("Sample 2 Output: " + Arrays.toString(longestStreak(costs2, budget2)));
    }
}