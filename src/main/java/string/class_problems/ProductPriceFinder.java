public class ProductPriceFinder {

    public static int findLowerBound(int[] prices, int target) {
        int low = 0, high = prices.length - 1;
        int ans = prices.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static int findUpperBound(int[] prices, int target) {
        int low = 0, high = prices.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] <= target) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int lowRange = 150;
        int highRange = 300;

        int lowerBoundIdx = findLowerBound(prices, lowRange);
        int upperBoundIdx = findUpperBound(prices, highRange);

        System.out.println("Lower bound index " + lowerBoundIdx);
        System.out.println("Upper bound index " + upperBoundIdx);

        if (lowerBoundIdx <= upperBoundIdx && lowerBoundIdx < prices.length) {
            int count = upperBoundIdx - lowerBoundIdx + 1;
            StringBuilder sb = new StringBuilder();
            for (int i = lowerBoundIdx; i <= upperBoundIdx; i++) {
                sb.append(prices[i]);
                if (i < upperBoundIdx) sb.append(", ");
            }
            System.out.println("Count " + count + " (products with prices " + sb.toString() + ")");
        } else {
            System.out.println("Count 0");
        }
    }
}