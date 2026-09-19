public class CommunityLibraryCheckoutSystem {

    static abstract class LibraryItem {
        private final int itemId;
        private static int itemCounter = 0;

        public LibraryItem() {
            itemCounter++;
            itemId = itemCounter;
        }

        public String getItemId() {
            return String.valueOf(itemId);
        }

        public abstract int getLoanPeriodDays();
    }

    interface Renewable {
        String renew();
    }

    interface Reservable {
        String reserve();
    }

    static class Textbook extends LibraryItem
            implements Renewable, Reservable {

        String title;

        public Textbook(String title) {
            this.title = title;
        }

        @Override
        public int getLoanPeriodDays() {
            return 14;
        }

        @Override
        public String renew() {
            return title + " renewed";
        }

        @Override
        public String reserve() {
            return title + " reserved";
        }
    }

    static class Magazine extends LibraryItem
            implements Renewable {

        String title;

        public Magazine(String title) {
            this.title = title;
        }

        @Override
        public int getLoanPeriodDays() {
            return 7;
        }

        @Override
        public String renew() {
            return title + " renewed";
        }
    }

    static class DigitalPass implements Renewable {

        String resourceName;

        public DigitalPass(String resourceName) {
            this.resourceName = resourceName;
        }

        @Override
        public String renew() {
            return resourceName + " renewed";
        }
    }

    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items) {
            System.out.println(item.getLoanPeriodDays());
        }
    }

    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) {
            Reservable r = (Reservable) o;
            return r.reserve();
        }

        return "Reservation not supported";
    }

    public static void main(String[] args) {

        Textbook t = new Textbook("Java Fundamentals");

        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());

        Magazine m = new Magazine("Tech Monthly");

        System.out.println(reserveIfSupported(m));

        DigitalPass d =
                new DigitalPass("E-Journal Access");

        System.out.println(reserveIfSupported(d));

        LibraryItem ref = t;

        System.out.println(reserveIfSupported(ref));
    }
}