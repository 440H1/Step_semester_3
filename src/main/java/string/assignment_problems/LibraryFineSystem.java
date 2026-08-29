import java.util.Scanner;

class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        if (daysOverdue > 0)
            return daysOverdue * 5;
        return 0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue issue : issues)
            total += issue.fineAmount();

        return total;
    }
}

public class LibraryFineSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        System.out.println(n + "books, daysOverdue:\n");

        BookIssue[] issues = new BookIssue[n];

        for (int i = 0; i < n; i++) {
            String title = sc.nextLine();
            int daysOverdue = sc.nextInt();
            sc.nextLine();

            issues[i] = new BookIssue(title, "", daysOverdue);
        }

        for (BookIssue issue : issues) {
            System.out.print(issue.title + " - " + issue.daysOverdue + " days - ");

            if (issue.isSeverelyOverdue())
                System.out.println("Severely overdue");
            else
                System.out.println("OK");
        }

        System.out.println("Total fine collected: Rs " +
                BookIssue.totalFineCollected(issues));

        sc.close();
    }
}