public class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn;
    public BusTicket(String passengerName, String destination) {
        if (!isValidField(passengerName)) {
            throw new IllegalArgumentException("Invalid passenger name");
        }
        if (!isValidField(destination)) {
            throw new IllegalArgumentException("Invalid destination");
        }
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }
    private static boolean isValidField(String field) {
        if (field == null) {
            return false;
        }
        String trimmed = field.trim();
        if (trimmed.isEmpty()) {
            return false;
        }
        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }
    void markCheckedIn() {
        if (checkedIn) {
            System.out.println("Reject: " + passengerName + " already checked in");
            return;
        }
        checkedIn = true;
    }
    String getPassengerName() {
        return passengerName;
    }
    String getDestination() {
        return destination;
    }
    static void processBatch(String[][] rawBookings) {
        BusTicket[] accepted = new BusTicket[rawBookings.length];
        int acceptedCount = 0;
        int rejectedCount = 0;
        int duplicateCount = 0;
        for (int i = 0; i < rawBookings.length; i++) {
            BusTicket ticket;
            try {
                ticket = new BusTicket(rawBookings[i][0], rawBookings[i][1]);
            } catch (IllegalArgumentException e) {
                rejectedCount++;
                continue;
            }
            boolean isDuplicate = false;
            for (int j = 0; j < acceptedCount; j++) {
                if (accepted[j].getPassengerName().equals(ticket.getPassengerName()) &&
                        accepted[j].getDestination().equals(ticket.getDestination())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                duplicateCount++;
                continue;
            }
            accepted[acceptedCount] = ticket;
            acceptedCount++;
        }
        System.out.println("Valid: " + acceptedCount + " | Rejected: " + rejectedCount + " | Duplicates skipped: " + duplicateCount);
    }
    public static void main(String[] args) {
        String[][] rawBookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };
        processBatch(rawBookings);
    }
}