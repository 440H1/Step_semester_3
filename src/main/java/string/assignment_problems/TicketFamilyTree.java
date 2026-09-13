public class TicketFamilyTree {

    static class EventTicket {
        protected double basePrice;
        protected double balanceDue;

        EventTicket(String attendeeId, double basePrice) {
            this.basePrice = basePrice;
            this.balanceDue = basePrice;
        }

        void pay(double amount) {
            balanceDue -= amount;
        }

        double getBalanceDue() {
            return balanceDue;
        }

        void printTicket() {
            System.out.println("Standard Event Ticket | Balance Due: " + balanceDue);
        }
    }

    static class WorkshopTicket extends EventTicket {
        protected String track;

        WorkshopTicket(String attendeeId, double basePrice, String track) {
            super(attendeeId, basePrice);
            this.track = track;
        }

        @Override
        void printTicket() {
            System.out.println("Workshop Ticket | Track: " + track + " | Balance Due: " + balanceDue);
        }
    }

    static class PremiumWorkshopTicket extends WorkshopTicket {
        private double kitFee;

        PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
            super(attendeeId, basePrice, track);
            this.kitFee = kitFee;
        }

        @Override
        void printTicket() {
            System.out.println("Premium Workshop Ticket | Track: " + track + " | Kit Fee: " + kitFee + " | Balance Due: " + balanceDue);
        }
    }

    static class HackathonTicket extends EventTicket {
        private String teamName;

        HackathonTicket(String attendeeId, double basePrice, String teamName) {
            super(attendeeId, basePrice);
            this.teamName = teamName;
        }

        @Override
        void printTicket() {
            System.out.println("Hackathon Ticket | Team: " + teamName + " | Balance Due: " + balanceDue);
        }
    }

    static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket)
            return "Multilevel descendant (3 generations deep)";

        if (ticket instanceof HackathonTicket)
            return "Hierarchical sibling (independent branch)";

        if (ticket instanceof WorkshopTicket)
            return "Intermediate descendant";

        return "Base generation";
    }

    static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0;

        for (EventTicket ticket : tickets)
            total += ticket.getBalanceDue();

        return total;
    }

    public static void main(String[] args) {
        EventTicket standardTicket = new EventTicket("STU1", 500);
        WorkshopTicket workshopTicket = new WorkshopTicket("STU2", 1200, "AI/ML");
        PremiumWorkshopTicket premiumTicket = new PremiumWorkshopTicket("STU3", 2000, "Cloud Native", 300);
        HackathonTicket hackathonTicket = new HackathonTicket("STU4", 800, "Byte Force");

        standardTicket.printTicket();
        workshopTicket.printTicket();
        premiumTicket.printTicket();
        hackathonTicket.printTicket();

        System.out.println(classifyGeneration(premiumTicket));
        System.out.println(classifyGeneration(hackathonTicket));

        EventTicket[] tickets = {standardTicket, workshopTicket, premiumTicket, hackathonTicket};
        System.out.println(getTotalBalanceDue(tickets));
    }
}