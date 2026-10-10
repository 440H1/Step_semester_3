import java.util.*;

public class StudentMarksGrid {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};

        Map<String, int[]> studentMarks = new LinkedHashMap<>();
        studentMarks.put("Anu", new int[]{80, 90, 70});
        studentMarks.put("Ravi", new int[]{60, 85});
        studentMarks.put("Meena", new int[]{95, 75, 88});

        StringBuilder totals = new StringBuilder("Totals ");
        boolean first = true;
        for (Map.Entry<String, int[]> entry : studentMarks.entrySet()) {
            int sum = 0;
            for (int mark : entry.getValue()) {
                sum += mark;
            }
            if (!first) totals.append(", ");
            totals.append(entry.getKey()).append(" ").append(sum);
            first = false;
        }
        System.out.println(totals);

        StringBuilder toppers = new StringBuilder("toppers ");
        for (int i = 0; i < subjects.length; i++) {
            String topperName = "";
            int maxMark = -1;
            for (Map.Entry<String, int[]> entry : studentMarks.entrySet()) {
                int[] marks = entry.getValue();
                if (i < marks.length) {
                    if (marks[i] > maxMark) {
                        maxMark = marks[i];
                        topperName = entry.getKey();
                    }
                }
            }
            if (i > 0) toppers.append(", ");
            toppers.append(subjects[i]).append(" ").append(topperName).append(" ").append(maxMark);
        }
        System.out.println(toppers);
    }
}