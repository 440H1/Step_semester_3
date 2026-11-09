import java.util.Stack;
import java.util.Arrays;

public class CanteenPriceStreak {

    public static int[] priceStreak(int[] price) {
        int n = price.length;
        int[] streak = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() &&
                    price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                streak[i] = i + 1;
            } else {
                streak[i] = i - stack.peek();
            }

            stack.push(i);
        }

        return streak;
    }

    public static void main(String[] args) {

        int[] price1 = {100, 80, 60, 70, 60, 75, 85};
        int[] price2 = {10, 20, 30};

        System.out.println(Arrays.toString(priceStreak(price1)));
        System.out.println(Arrays.toString(priceStreak(price2)));
    }
}