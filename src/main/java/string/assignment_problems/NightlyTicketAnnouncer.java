public class NightlyTicketAnnouncer {

    static class EventTicket {
        protected double balanceDue;

        EventTicket(double basePrice) {
            balanceDue = basePrice;
        }

        void printTicket() {
            System.out.print("Standard | Balance: " + balanceDue);
        }
    }

    static class WorkshopTicket extends EventTicket {
        private String track;

        WorkshopTicket(double basePrice, String track) {
            super(basePrice);
            this.track = track;
        }

        @Override
        void printTicket() {
            System.out.print("Workshop | Track: " + track + " | Balance: " + balanceDue);
        }

        String getTrack() {
            return track;
        }
    }

    static String batchPrint(EventTicket[] tickets) {
        StringBuilder result = new StringBuilder();

        for (EventTicket ticket : tickets) {
            ticket.printTicket();

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshop = (WorkshopTicket) ticket;
                result.append("Workshop | Track: ").append(workshop.getTrack()).append(" | Balance: ").append(workshop.balanceDue);
                result.append(" [Track via downcast: ").append(workshop.getTrack()).append("] | ");
            } else {
                result.append("Standard | Balance: ").append(ticket.balanceDue).append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        EventTicket standard = new EventTicket(500);
        WorkshopTicket workshop = new WorkshopTicket(1200, "AI/ML");

        EventTicket[] tickets = {standard, workshop};

        System.out.println(batchPrint(tickets));
    }
}