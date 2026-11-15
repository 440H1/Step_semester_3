import java.util.*;

public class PlacementShortlist {

    static class Candidate {
        String name;
        int[] scores;
        int total;
        int bestGain;
        int startTest;
        int endTest;

        Candidate(String name, int[] scores) {
            this.name = name;
            this.scores = scores;
            total = 0;

            for (int score : scores) {
                total += score;
            }

            findBestClimb();
        }

        void findBestClimb() {
            bestGain = Integer.MIN_VALUE;

            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 6; j++) {
                    int gain = scores[j] - scores[i];

                    if (gain > bestGain) {
                        bestGain = gain;
                        startTest = i + 1;
                        endTest = j + 1;
                    }
                }
            }
        }
    }

    static void mergeSort(Candidate[] candidates, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(candidates, left, mid);
        mergeSort(candidates, mid + 1, right);

        Candidate[] temp = new Candidate[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (candidates[i].total >= candidates[j].total) {
                temp[k++] = candidates[i++];
            } else {
                temp[k++] = candidates[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = candidates[i++];
        }

        while (j <= right) {
            temp[k++] = candidates[j++];
        }

        for (i = 0; i < temp.length; i++) {
            candidates[left + i] = temp[i];
        }
    }

    static int countCutoff(Candidate[] candidates, int target) {
        int low = 0;
        int high = candidates.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (candidates[mid].total >= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    static void findPair(Candidate[] candidates, int target) {
        int left = 0;
        int right = candidates.length - 1;

        while (left < right) {
            int sum = candidates[left].total + candidates[right].total;

            if (sum == target) {
                System.out.println("PAIR " + target + ": "
                        + candidates[left].name + " + "
                        + candidates[right].name);
                return;
            } else if (sum > target) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println("PAIR " + target + ": no pair");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Candidate[] candidates = new Candidate[n];

        for (int i = 0; i < n; i++) {
            String name = sc.next();

            int[] scores = new int[6];

            for (int j = 0; j < 6; j++) {
                scores[j] = sc.nextInt();
            }

            candidates[i] = new Candidate(name, scores);
        }

        mergeSort(candidates, 0, n - 1);

        System.out.println("Shortlist:");

        for (int i = 0; i < n; i++) {
            Candidate c = candidates[i];

            System.out.println((i + 1) + ". " + c.name + " "
                    + c.total + " | best climb +" + c.bestGain
                    + " (test " + c.startTest + " to " + c.endTest + ")");
        }

        while (sc.hasNext()) {
            String command = sc.next();
            int target = sc.nextInt();

            if (command.equals("CUTOFF")) {
                System.out.println("CUTOFF " + target + ": "
                        + countCutoff(candidates, target) + " candidates");
            } else if (command.equals("PAIR")) {
                findPair(candidates, target);
            }
        }

        sc.close();
    }
}
