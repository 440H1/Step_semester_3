public class LateRegistrationPenaltyManager {

    static class EventTicket {
        protected double balanceDue;
        private double[] lateFeeHistory = new double[10];
        private int feeCount = 0;

        EventTicket(double basePrice) {
            balanceDue = basePrice;
        }

        void pay(double amount) {
            balanceDue -= amount;
        }

        protected void applyLateFee(double amount) {
            balanceDue += amount;
            lateFeeHistory[feeCount++] = amount;
        }

        double getBalanceDue() {
            return balanceDue;
        }

        double[] getLateFeeHistory() {
            double[] copy = new double[feeCount];

            for (int i = 0; i < feeCount; i++)
                copy[i] = lateFeeHistory[i];

            return copy;
        }
    }

    static class WorkshopTicket extends EventTicket {

        WorkshopTicket(double basePrice) {
            super(basePrice);
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        history[0] = 999;

        double[] newHistory = w.getLateFeeHistory();

        System.out.println("[" + newHistory[0] + "]");
        System.out.println("[" + newHistory[0] + "]");
    }
}