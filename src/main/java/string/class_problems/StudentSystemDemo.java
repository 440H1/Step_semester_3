class SrmStudentBroken {
    // BROKEN: every field marked static
    static String name;
    static String regNo;
    static int attendance;
    SrmStudentBroken(String name, String regNo, int attendance) {
        SrmStudentBroken.name = name;
        SrmStudentBroken.regNo = regNo;
        SrmStudentBroken.attendance = attendance;
        /*name is wrong static: each student needs their OWN name, but
        static means there is only ONE copy shared by every instance*/
        /*regNo is wrong static: same reason, every student needs a
        distinct regNo, not one shared value*/
        /*attendance is wrong static: each student's attendance is
        personal data, not something the whole class should share*/
    }
    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }
}
class SrmStudent {
    // FIXED: instance fields — each object gets its own copy
    private String name;
    private String regNo;
    private int attendance;
    // FIXED: static fields — shared across all students
    private static int admissionCount = 0;
    private static String university = "SRM Institute of Science and Technology";
    public SrmStudent(String name, int attendance) {
        this.name = name;
        admissionCount++;
        this.regNo = "RA23110030" + String.format("%03d", admissionCount);
        this.attendance = attendance;
    }
    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }
    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}
public class StudentSystemDemo {
    public static void main(String[] args) {
        System.out.println("--- Broken version ---");
        SrmStudentBroken ravi = new SrmStudentBroken("Ravi", "RA23110030011", 82);
        SrmStudentBroken meera = new SrmStudentBroken("Meera", "RA23110030012", 74);
        ravi.printIdCard();
        meera.printIdCard();
        System.out.println();
        System.out.println("--- Fixed version ---");
        SrmStudent fixedRavi = new SrmStudent("Ravi", 82);
        SrmStudent fixedMeera = new SrmStudent("Meera", 74);
        fixedRavi.printIdCard();
        fixedMeera.printIdCard();
        SrmStudent.printTotalAdmissions();
    }
}