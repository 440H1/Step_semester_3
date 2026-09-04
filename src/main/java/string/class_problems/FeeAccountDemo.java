class FeeAccount {
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
    String getRegNo() {
        return regNo;
    }
}

class HostelFeeAccount extends FeeAccount {
    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }
    void payInTwoInstallments(double amount) {
        double half = amount / 2;
        pay(half);
        pay(half);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;
    public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }
    double effectiveDue() {
        return getDue() * (1 - scholarshipPercent / 100);
    }
}
public class FeeAccountDemo {
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("REG001", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("REG002", 200000, 0);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("REG003", 180000, 0, 20);
        hostel.payInTwoInstallments(60000);
        FeeAccount[] accounts = { plain, hostel, scholarship };
        for (int i = 0; i < accounts.length; i++) {
            FeeAccount acc = accounts[i];
            if (acc instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount sfa = (ScholarshipFeeAccount) acc;
                System.out.println("Scholarship account effective due: Rs " + sfa.effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + acc.getDue());
            } else {
                System.out.println("Plain account due: Rs " + acc.getDue());
            }
        }
    }
}