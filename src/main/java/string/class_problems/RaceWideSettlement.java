public class RaceWideSettlement {

    static class RaceEntry {
        String bibNumber;
        double entryFee;
        final int entryCode;

        private static int bibCounter = 0;
        private String paymentMode;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().isEmpty()
                    || bibNumber.length() < 4) {
                throw new IllegalArgumentException();
            }

            this.bibNumber = bibNumber;
            this.entryFee = entryFee;

            bibCounter++;
            this.entryCode = bibCounter;
        }

        public void pay(double amount) {
            entryFee -= amount;
        }

        public void pay(double amount, String mode) {
            pay(amount);
            paymentMode = mode;
            System.out.println("Paying via " + mode);
        }

        public double getBalanceDue() {
            return entryFee;
        }

        public static boolean isValidDiscountCode(String code) {
            if (code == null || code.length() != 5) {
                return false;
            }

            if (code.charAt(0) != 'M') {
                return false;
            }

            for (int i = 1; i <= 3; i++) {
                if (!Character.isDigit(code.charAt(i))) {
                    return false;
                }
            }

            return Character.isUpperCase(code.charAt(4));
        }

        public static int getBibCounter() {
            return bibCounter;
        }
    }

    static class RunnerEntry extends RaceEntry {
        String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }
    }

    static class EliteRunnerEntry extends RunnerEntry {
        double sponsorBonus;

        public EliteRunnerEntry(String bibNumber, double entryFee,
                                String category, double sponsorBonus) {
            super(bibNumber, entryFee, category);
            this.sponsorBonus = sponsorBonus;
        }
    }

    static class RelayTeamEntry extends RaceEntry {
        int teamSize;

        public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }
    }

    static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int skipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                skipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + skipped + " null skipped | "
                + relay + " relay | "
                + individual + " individual";
    }

    public static void main(String[] args) {

        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));

        RunnerEntry runner =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        EliteRunnerEntry elite =
                new EliteRunnerEntry("BIB3001", 150,
                        "Elite Full Marathon", 500);

        RelayTeamEntry relay =
                new RelayTeamEntry("BIB4001", 300, 4);

        runner.pay(10, "UPI");

        System.out.println(
                settleNight(new RaceEntry[]{elite, null, relay})
        );

        System.out.println(RaceEntry.getBibCounter());
    }
}