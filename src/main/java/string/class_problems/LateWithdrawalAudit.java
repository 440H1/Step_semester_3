public class LateWithdrawalAudit {

    static class RaceEntry {
        String bibNumber;
        double entryFee;
        private double[] lateFeeHistory = new double[10];
        private int lateFeeCount = 0;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().isEmpty()
                    || bibNumber.length() < 4) {
                throw new IllegalArgumentException();
            }

            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
        }

        public void pay(double amount) {
            entryFee -= amount;
        }

        public double getBalanceDue() {
            return entryFee;
        }

        protected void applyLateFee(double amount) {
            entryFee += amount;
            lateFeeHistory[lateFeeCount++] = amount;
        }

        public double[] getLateFeeHistory() {
            double[] copy = new double[lateFeeCount];

            for (int i = 0; i < lateFeeCount; i++) {
                copy[i] = lateFeeHistory[i];
            }

            return copy;
        }
    }

    static class RunnerEntry extends RaceEntry {
        String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        RunnerEntry r =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);
        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();

        System.out.println(java.util.Arrays.toString(history));

        history[0] = 999;

        System.out.println(java.util.Arrays.toString(r.getLateFeeHistory()));
    }
}