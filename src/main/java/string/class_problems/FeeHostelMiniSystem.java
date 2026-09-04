
public class FeeHostelMiniSystem {
    static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        public FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }
        void pay(double amount) {
            if (amount <= 0) {
                System.out.println("Invalid payment amount");
                return;
            }
            amountPaid += amount;
        }
        double getDue() {
            return totalFee - amountPaid;
        }
    }
    static class HostelFeeAccount extends FeeAccount {

        public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }
        void payInTwoInstallments(double amount) {
            double half = amount / 2;
            pay(half);
            pay(half);
        }
    }
    static class HostelRoom {
        String roomNo;
        int beds;
        int occupied;
        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }
        void allot(String name) {
            occupied++;
        }
        static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
            for (int i = 0; i < rooms.length; i++) {
                if (rooms[i].occupied < rooms[i].beds) {
                    return rooms[i];
                }
            }
            return null;
        }
        static void safeAllot(HostelRoom[] rooms, String studentName) {
            HostelRoom room = findAvailableRoom(rooms);
            if (room == null) {
                System.out.println("No rooms available for " + studentName);
                return;
            }
            room.allot(studentName);
        }
    }
    static class SrmStudent {
        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;
        static int totalStudents = 0;
        public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = room;
            totalStudents++;
        }
        String fullStatus() {
            String roomInfo = (room == null) ? "unallotted" : room.roomNo;
            return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomInfo;
        }
        static void totalStudents() {
            System.out.println("Total students: " + totalStudents);
        }
    }
    public static void main(String[] args) {
        HostelFeeAccount raviFee = new HostelFeeAccount("REG001", 200000, 0);
        HostelFeeAccount anithaFee = new HostelFeeAccount("REG002", 200000, 0);
        HostelFeeAccount karthikFee = new HostelFeeAccount("REG003", 200000, 0);
        raviFee.payInTwoInstallments(120000);
        anithaFee.payInTwoInstallments(40000);
        karthikFee.pay(-5000); // rejected payment
        HostelRoom[] rooms = {
                new HostelRoom("C-214", 1, 0),
                new HostelRoom("C-507", 1, 0)
        };
        HostelRoom raviRoom = HostelRoom.findAvailableRoom(rooms);
        HostelRoom.safeAllot(rooms, "Ravi");
        HostelRoom anithaRoom = HostelRoom.findAvailableRoom(rooms);
        HostelRoom.safeAllot(rooms, "Anitha");
        HostelRoom.safeAllot(rooms, "Karthik");
        HostelRoom karthikRoom = HostelRoom.findAvailableRoom(rooms);
        SrmStudent ravi = new SrmStudent("Ravi", "REG001", raviFee, raviRoom);
        SrmStudent anitha = new SrmStudent("Anitha", "REG002", anithaFee, anithaRoom);
        SrmStudent karthik = new SrmStudent("Karthik", "REG003", karthikFee, karthikRoom);
        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());
        SrmStudent.totalStudents();
    }
}