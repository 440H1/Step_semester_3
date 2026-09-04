import java.util.Scanner;
class StreakAnalyzer {
    void findLongestStreak(String signalLog) {
        int maxLen = 1;
        int currentLen = 1;
        char maxChar = signalLog.charAt(0);
        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentLen++;
            } else {
                currentLen = 1;
            }

            if (currentLen > maxLen) {
                maxLen = currentLen;
                maxChar = signalLog.charAt(i);
            }
        }
        System.out.println("Longest Streak: '" + maxChar + "' repeated " + maxLen + " times");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String log = sc.nextLine();
        new StreakAnalyzer().findLongestStreak(log);
    }
}