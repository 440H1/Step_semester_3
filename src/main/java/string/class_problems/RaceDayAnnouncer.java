public class RaceDayAnnouncer {

    static class RaceEntry {
        String bibNumber;
        double entryFee;

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

        public String announce() {
            return "Race Entry | Bib: " + bibNumber
                    + " | Balance: " + entryFee;
        }
    }

    static class RunnerEntry extends RaceEntry {
        String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        public String announce() {
            return "Runner Entry | Bib: " + bibNumber
                    + " | Category: " + category
                    + " | Balance: " + entryFee;
        }
    }

    static class RelayTeamEntry extends RaceEntry {
        int teamSize;

        public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        @Override
        public String announce() {
            return "Relay Team | Bib: " + bibNumber
                    + " | Team Size: " + teamSize
                    + " | Balance: " + entryFee;
        }
    }

    static String announceAll(RaceEntry[] entries) {
        StringBuilder report = new StringBuilder();

        for (RaceEntry entry : entries) {
            report.append(entry.announce());

            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                report.append(" [Team size via downcast: ")
                        .append(relay.teamSize)
                        .append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {
        RunnerEntry runner =
                new RunnerEntry("BIB2001", 90, "Open 10K");

        RelayTeamEntry relay =
                new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] fleet = {runner, relay};

        System.out.println(announceAll(fleet));
    }
}