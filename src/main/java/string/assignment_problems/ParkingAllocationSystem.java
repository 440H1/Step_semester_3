

public class ParkingAllocationSystem {
    static class Employee {
        private String empId;
        private String empName;
        private double salary;

        Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }

        String getEmpName() {
            return empName;
        }
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;

        ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class ParkingSlot {
        String slotNo;
        int capacity;
        int occupiedCount;

        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        void allot(String vehicleNo) {
            if (occupiedCount < capacity)
                occupiedCount++;
        }

        static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
            for (ParkingSlot slot : slots) {
                if (slot != null && slot.occupiedCount < slot.capacity)
                    return slot;
            }
            return null;
        }

        static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
            ParkingSlot slot = findAvailableSlot(slots);

            if (slot != null)
                slot.allot(vehicleNo);
        }
    }

    static class CompanyEmployeeRecord {
        String name;
        String empId;
        Employee employee;
        ParkingSlot slot;

        static int totalRecords = 0;

        CompanyEmployeeRecord(String name, String empId,
                              Employee employee, ParkingSlot slot) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;
            totalRecords++;
        }

        String fullProfile() {
            double pay;

            if (employee instanceof ManagerEmployee)
                pay = ((ManagerEmployee) employee).effectiveSalary();
            else
                pay = employee.getSalary();

            String slotNumber;

            if (slot != null)
                slotNumber = slot.slotNo;
            else
                slotNumber = "no parking assigned";

            return name + " | Pay: Rs " + pay + " | Slot: " + slotNumber;
        }

        static void totalRecords() {
            System.out.println("Total records: " + totalRecords);
        }
    }
    public static void main(String[] args) {

        ParkingSlot[] slots = {
                new ParkingSlot("A1", 1, 0),
                new ParkingSlot("A2", 1, 0)
        };

        Employee e1 = new ManagerEmployee("E101", "Divya", 70000, 8000);
        Employee e2 = new Employee("E102", "Karan", 40000);
        Employee e3 = new Employee("E103", "Meera", 10000);

        ParkingSlot.safeAllot(slots, "E101");
        ParkingSlot.safeAllot(slots, "E102");

        ParkingSlot slot1 = slots[0];
        ParkingSlot slot2 = slots[1];

        CompanyEmployeeRecord r1 =
                new CompanyEmployeeRecord("Divya", "E101", e1, slot1);

        CompanyEmployeeRecord r2 =
                new CompanyEmployeeRecord("Karan", "E102", e2, slot2);

        CompanyEmployeeRecord r3 =
                new CompanyEmployeeRecord("Meera", "E103", e3, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());

        CompanyEmployeeRecord.totalRecords();
    }
}