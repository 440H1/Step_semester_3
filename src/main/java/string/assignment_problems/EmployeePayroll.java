import java.util.Scanner;

class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeePayroll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Plain:\n\tsalary ");
        double plainSalary = sc.nextDouble();
        System.out.print("Manager:\n\tsalary ");
        double managerSalary = sc.nextDouble();
        System.out.print("\tteamBonus ");
        double teamBonus = sc.nextDouble();
        System.out.print("Intern:\n\tsalary ");
        double internSalary = sc.nextDouble();
        System.out.print("\tstipendCap ");
        double stipendCap = sc.nextDouble();

        Employee plain = new Employee(1, "Plain", plainSalary);
        ManagerEmployee manager =
                new ManagerEmployee(2, "Manager", managerSalary, teamBonus);
        InternEmployee intern =
                new InternEmployee(3, "Intern", internSalary, stipendCap);

        System.out.println("Plain employee pay: Rs " + plain.getSalary());

        if (manager instanceof ManagerEmployee)
            System.out.println("Manager effective pay: Rs " + manager.effectiveSalary());

        if (intern instanceof InternEmployee)
            System.out.println("Intern effective pay: Rs " + intern.effectiveSalary());

        sc.close();
    }
}
