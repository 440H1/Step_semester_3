public class FesticketSettlement{

    static class EventTicket {
        private static int ticketsIssued = 0;
        public final String ticketId;
        protected double balanceDue;

        EventTicket(double basePrice) {
            ticketsIssued++;
            ticketId = "TCK-" + (1000 + ticketsIssued);
            balanceDue = basePrice;
        }

        static String processNightlySettlement(EventTicket[] tickets) {
            int processed = 0;
            int skipped = 0;
            int group = 0;
            int individual = 0;

            for (EventTicket ticket : tickets) {
                if (ticket == null) {
                    skipped++;
                    continue;
                }

                processed++;

                if (ticket instanceof GroupTicket)
                    group++;
                else
                    individual++;
            }

            return processed + " processed | " + skipped + " null skipped | " + group + " group | " + individual + " individual";
        }
        void pay(double amount) {
            balanceDue -= amount;
        }

        void pay(double amount, String mode) {
            System.out.println("Payment Mode: " + mode);
            pay(amount);
        }

        double getBalanceDue() {
            return balanceDue;
        }

        static boolean isValidPromoCode(String code) {
            if (code == null || code.length() != 5)
                return false;

            if (code.charAt(0) != 'F')
                return false;

            if (!Character.isDigit(code.charAt(1)))
                return false;

            if (!Character.isDigit(code.charAt(2)))
                return false;

            if (!Character.isDigit(code.charAt(3)))
                return false;

            if (!Character.isUpperCase(code.charAt(4)))
                return false;

            return true;
        }

        static int getTicketsIssued() {
            return ticketsIssued;
        }
    }

    static class GroupTicket extends EventTicket {
        private int groupSize;

        GroupTicket(double basePrice, int groupSize) {
            super(basePrice);
            this.groupSize = groupSize;
        }
    }



    public static void main(String[] args) {
        EventTicket t1 = new EventTicket(500);

        System.out.println(t1.ticketId);
        System.out.println(EventTicket.getTicketsIssued());

        System.out.println(EventTicket.isValidPromoCode("F123A"));
        System.out.println(EventTicket.isValidPromoCode("F12A"));
        System.out.println(EventTicket.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");
        System.out.println(t1.getBalanceDue());

        EventTicket[] tickets = {new GroupTicket(2000, 5), null, new EventTicket(500)};

        System.out.println(EventTicket.processNightlySettlement(tickets));
    }
}