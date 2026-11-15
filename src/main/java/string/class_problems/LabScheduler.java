import java.util.*;

public class LabScheduler {

    static class Job {
        String name;
        int remainingTime;

        Job(String name, int time) {
            this.name = name;
            this.remainingTime = time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int quantum = sc.nextInt();
        int n = sc.nextInt();

        Queue<Job> jobs = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int time = sc.nextInt();

            jobs.add(new Job(name, time));
        }

        int currentTime = 0;

        StringBuilder slices = new StringBuilder();
        StringBuilder finish = new StringBuilder();

        while (!jobs.isEmpty()) {

            Job current = jobs.poll();

            int start = currentTime;

            int runTime = Math.min(quantum, current.remainingTime);

            currentTime += runTime;
            current.remainingTime -= runTime;

            if (slices.length() > 0) {
                slices.append(" ");
            }

            slices.append(current.name)
                    .append("(")
                    .append(start)
                    .append("-")
                    .append(currentTime)
                    .append(")");

            if (current.remainingTime > 0) {
                jobs.add(current);
            } else {
                if (finish.length() > 0) {
                    finish.append(", ");
                }

                finish.append(current.name)
                        .append(" ")
                        .append(currentTime);
            }
        }

        System.out.println(slices);
        System.out.println("finish " + finish);

        sc.close();
    }
}
