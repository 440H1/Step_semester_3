import java.util.Scanner;
public class AttendanceSystem {
    private String name;
    private String regNo;
    private int attendance;
    public AttendanceSystem(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }
    void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }
    boolean isEligible() {
        return attendance >= 75;
    }
    int getAttendance() {
        return attendance;
    }
    String getName() {
        return name;
    }
    // Static method: belongs to itself
    static double classAverage(AttendanceSystem[] students) {
        int total = 0;
        for (int i = 0; i < students.length; i++) {
            total += students[i].getAttendance();
        }
        return (double) total / students.length;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5;
        AttendanceSystem[] students = new AttendanceSystem[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            String name = sc.nextLine();
            System.out.print("Enter reg no for student " + (i + 1) + ": ");
            String regNo = sc.nextLine();
            System.out.print("Enter attendance % for student " + (i + 1) + ": ");
            int attendance = Integer.parseInt(sc.nextLine());

            students[i] = new AttendanceSystem(name, regNo, attendance);
        }
        for (int i = 0; i < students.length; i++) {
            String status = students[i].isEligible() ? "Eligible" : "Detained";
            System.out.println(students[i].getName() + " - " + students[i].getAttendance() + "% - " + status);
        }
        double avg = AttendanceSystem.classAverage(students);
        System.out.println("Class average: " + avg + "%");
        sc.close();
    }
}