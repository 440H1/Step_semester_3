public class HomeControlPanel {

    static abstract class HomeDevice {
        private static int counter = 1001;
        private final String serialNumber;

        protected HomeDevice() {
            serialNumber = "HD-" + counter++;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public abstract void activate();
    }

    static interface RemoteControllable {
        String connect(String appId);
    }

    static interface EnergyTrackable {
        double getConsumptionWatts();
    }

    static class WashingMachine extends HomeDevice
            implements RemoteControllable, EnergyTrackable {

        private final double consumptionWatts;

        public WashingMachine(double consumptionWatts) {
            this.consumptionWatts = consumptionWatts;
        }

        @Override
        public void activate() {
            System.out.println("Washing machine " + getSerialNumber() + " started a cycle");
        }

        @Override
        public String connect(String appId) {
            return getSerialNumber() + " connected to " + appId;
        }

        @Override
        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    static class Refrigerator extends HomeDevice
            implements EnergyTrackable {

        private final double consumptionWatts;

        public Refrigerator(double consumptionWatts) {
            this.consumptionWatts = consumptionWatts;
        }

        @Override
        public void activate() {
            System.out.println("Refrigerator " + getSerialNumber() + " activated");
        }

        @Override
        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    static class MobileApp implements RemoteControllable {
        private final String appName;

        public MobileApp(String appName) {
            this.appName = appName;
        }

        @Override
        public String connect(String appId) {
            return appName + " connected to " + appId;
        }
    }

    static void connectAll(RemoteControllable[] items, String appId) {
        for (RemoteControllable item : items) {
            System.out.println(item.connect(appId));
        }
    }

    static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            return ((EnergyTrackable) d).getConsumptionWatts();
        }
        return 0.0;
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(500.0);
        wm.activate();
        System.out.println(wm.connect("HomeConnect"));

        Refrigerator fridge = new Refrigerator(150.0);
        System.out.println(getConsumptionIfTrackable(fridge));

        MobileApp app = new MobileApp("HomeConnect App");
        System.out.println(app.connect("HomeConnect"));

        HomeDevice ref = wm;
        System.out.println(getConsumptionIfTrackable(ref));

        RemoteControllable[] items = {wm, app};
        connectAll(items, "HomeConnect");
    }
}
