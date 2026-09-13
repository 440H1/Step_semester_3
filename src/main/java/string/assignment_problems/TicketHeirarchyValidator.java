public class TicketHeirarchyValidator {

    static class EventTicket {
        protected String attendeeId;
        protected double basePrice;
        protected double balanceDue;

        public EventTicket(String attendeeId, double basePrice) {
            if (attendeeId == null || attendeeId.trim().length() < 4)
                throw new IllegalArgumentException("construction rejected");

            this.attendeeId = attendeeId;
            this.basePrice = basePrice;
            this.balanceDue = basePrice;
        }

        void pay(double amount) {
            balanceDue -= amount;
        }

        double getBalanceDue() {
            return balanceDue;
        }

        static String registerBatch(String[] attendeeIds, double basePrice) {
            int registered = 0;
            int rejected = 0;

            for (String id : attendeeIds) {
                try {
                    new EventTicket(id, basePrice);
                    registered++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            return "Registered: " + registered + " | Rejected: " + rejected;
        }
    }

    static class WorkshopTicket extends EventTicket {
        private String track;

        public WorkshopTicket(String attendeeId, double basePrice, String track) {
            super(attendeeId, basePrice);
            this.track = track;
        }
    }

    public static void main(String[] args) {
        try {
            new EventTicket("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());

        String[] ids = {"STU1", "ST1", "STU2", " ", "STU3"};
        System.out.println(EventTicket.registerBatch(ids, 500));
    }
}