public class EmployeeLeaveRequestManagement {

    abstract class Employee {
        String employeeName;

        Employee(String employeeName) {
            this.employeeName = employeeName;
        }

        abstract String getEmployeeType();
    }

    class FullTimeEmployee extends Employee {

        FullTimeEmployee(String employeeName) {
            super(employeeName);
        }

        String getEmployeeType() {
            return "Full-time";
        }
    }

    class PartTimeEmployee extends Employee {

        PartTimeEmployee(String employeeName) {
            super(employeeName);
        }

        String getEmployeeType() {
            return "Part-time";
        }
    }

    class ContractEmployee extends Employee {

        ContractEmployee(String employeeName) {
            super(employeeName);
        }

        String getEmployeeType() {
            return "Contract";
        }
    }

    class LeaveRequest {
        Employee employee;
        String startDate;
        String endDate;
        String status;

        LeaveRequest(Employee employee,
                     String startDate,
                     String endDate) {

            this.employee = employee;
            this.startDate = startDate;
            this.endDate = endDate;
            this.status = "Pending";
        }

        void approve() {
            if (status.equals("Pending")) {
                status = "Approved";

                System.out.println(
                        "Leave request for " +
                                employee.employeeName +
                                " approved. Status: " +
                                status
                );
            }
        }

        void reject() {
            if (status.equals("Pending")) {
                status = "Rejected";

                System.out.println(
                        "Leave request for " +
                                employee.employeeName +
                                " rejected. Status: " +
                                status
                );
            }
        }

        void changeToPending() {
            if (status.equals("Pending")) {
                return;
            }

            System.out.println(
                    "Cannot change status: " +
                            status +
                            " request cannot revert to Pending."
            );
        }

        void displayRequest() {
            System.out.println(
                    "Leave request submitted by " +
                            employee.employeeName +
                            " for " +
                            startDate +
                            " to " +
                            endDate +
                            ". Status: " +
                            status
            );
        }
    }

    class LeaveManager {

        void submitRequest(LeaveRequest request) {
            request.displayRequest();
        }

        void reviewRequest(LeaveRequest request,
                           String decision) {

            if (decision.equalsIgnoreCase("Approve")) {
                request.approve();
            } else if (decision.equalsIgnoreCase("Reject")) {
                request.reject();
            }
        }

        void changeToPending(LeaveRequest request) {
            request.changeToPending();
        }
    }

    public static void main(String[] args) {

        EmployeeLeaveRequestManagement system =
                new EmployeeLeaveRequestManagement();

        FullTimeEmployee john =
                system.new FullTimeEmployee("John Doe");

        PartTimeEmployee jane =
                system.new PartTimeEmployee("Jane Smith");

        LeaveRequest johnRequest =
                system.new LeaveRequest(
                        john,
                        "2024-10-10",
                        "2024-10-12"
                );

        LeaveRequest janeRequest =
                system.new LeaveRequest(
                        jane,
                        "2024-11-01",
                        "2024-11-05"
                );

        LeaveManager leaveManager =
                system.new LeaveManager();

        leaveManager.submitRequest(johnRequest);

        leaveManager.reviewRequest(
                johnRequest,
                "Approve"
        );

        leaveManager.submitRequest(janeRequest);

        leaveManager.changeToPending(johnRequest);
    }
}