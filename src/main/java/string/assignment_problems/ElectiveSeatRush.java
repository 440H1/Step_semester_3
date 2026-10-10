import java.util.*;

public class ElectiveSeatRush {

    interface CreditPolicy {
        int getCreditLimit();
        String getTypeName();
    }

    static class RegularStudentPolicy implements CreditPolicy {
        @Override
        public int getCreditLimit() {
            return 24;
        }

        @Override
        public String getTypeName() {
            return "Regular";
        }
    }

    static class HonorsStudentPolicy implements CreditPolicy {
        @Override
        public int getCreditLimit() {
            return 28;
        }

        @Override
        public String getTypeName() {
            return "Honors";
        }
    }

    static class ExchangeStudentPolicy implements CreditPolicy {
        @Override
        public int getCreditLimit() {
            return 20;
        }

        @Override
        public String getTypeName() {
            return "Exchange";
        }
    }

    static class Student {
        private String name;
        private CreditPolicy creditPolicy;
        private int currentCredits;

        public Student(String name, CreditPolicy creditPolicy, int currentCredits) {
            this.name = name;
            this.creditPolicy = creditPolicy;
            this.currentCredits = currentCredits;
        }

        public String getName() {
            return name;
        }

        public CreditPolicy getCreditPolicy() {
            return creditPolicy;
        }

        public int getCurrentCredits() {
            return currentCredits;
        }

        public boolean canAddCredits(int credits) {
            return (currentCredits + credits) <= creditPolicy.getCreditLimit();
        }

        public void addCredits(int credits) {
            this.currentCredits += credits;
        }

        public void removeCredits(int credits) {
            this.currentCredits -= credits;
        }
    }

    static class Elective {
        private String name;
        private int credits;
        private int capacity;
        private List<Student> enrolled = new ArrayList<>();
        private Queue<Student> waitlist = new LinkedList<>();

        public Elective(String name, int credits, int capacity) {
            this.name = name;
            this.credits = credits;
            this.capacity = capacity;
        }

        public String getName() {
            return name;
        }

        public int getCredits() {
            return credits;
        }

        public void enrollStudent(Student student) {
            if (enrolled.contains(student) || waitlist.contains(student)) {
                System.out.println("Registration failed: Student already in course or waitlist.");
                return;
            }

            if (!student.canAddCredits(credits)) {
                System.out.println("Enrollment failed: " + student.getName() + " would exceed the " +
                        student.getCreditPolicy().getTypeName() + " credit limit (" +
                        (student.getCurrentCredits() + credits) + "/" + student.getCreditPolicy().getCreditLimit() + ").");
                return;
            }

            if (enrolled.size() < capacity) {
                enrolled.add(student);
                student.addCredits(credits);
                System.out.println(student.getName() + " enrolled in " + name + " (credits: " +
                        student.getCurrentCredits() + "/" + student.getCreditPolicy().getCreditLimit() + ").");
            } else {
                waitlist.add(student);
                System.out.println(name + " is full. " + student.getName() + " added to waitlist (position " + waitlist.size() + ").");
            }
        }

        public void dropStudent(Student student) {
            if (!enrolled.contains(student)) {
                return;
            }

            enrolled.remove(student);
            student.removeCredits(credits);
            System.out.println(student.getName() + " dropped " + name + " (credits: " +
                    student.getCurrentCredits() + "/" + student.getCreditPolicy().getCreditLimit() + ").");

            while (!waitlist.isEmpty()) {
                Student next = waitlist.poll();
                if (next.canAddCredits(credits)) {
                    enrolled.add(next);
                    next.addCredits(credits);
                    System.out.println(next.getName() + " promoted from waitlist and enrolled in " + name + " (credits: " +
                            next.getCurrentCredits() + "/" + next.getCreditPolicy().getCreditLimit() + ").");
                    break;
                } else {
                    System.out.println("Promotion failed for " + next.getName() + ": Credit limit exceeded.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Elective cloud = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", new RegularStudentPolicy(), 20);
        Student ravi = new Student("Ravi", new HonorsStudentPolicy(), 22);
        Student neha = new Student("Neha", new ExchangeStudentPolicy(), 12);
        Student kiran = new Student("Kiran", new RegularStudentPolicy(), 22);

        cloud.enrollStudent(asha);
        cloud.enrollStudent(ravi);
        cloud.enrollStudent(neha);
        cloud.enrollStudent(kiran);

        cloud.dropStudent(asha);
    }
}