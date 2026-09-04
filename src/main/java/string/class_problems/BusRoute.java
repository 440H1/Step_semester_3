public class BusRoute implements Comparable<BusRoute> {
    private final String routeCode;
    private final String routeName;
    private final int priority;
    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }
    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 1);
    }
    @Override
    public int compareTo(BusRoute other) {
        int priorityCompare = Integer.compare(other.priority, this.priority);
        if (priorityCompare != 0) {
            return priorityCompare;
        }
        int caseInsensitiveCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (caseInsensitiveCompare != 0) {
            return caseInsensitiveCompare;
        }
        return this.routeCode.compareTo(other.routeCode);
    }
    String getRouteCode() {
        return routeCode;
    }
    static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] ranked = routes.clone();
        for (int i = 1; i < ranked.length; i++) {
            BusRoute key = ranked[i];
            int j = i - 1;

            while (j >= 0 && ranked[j].compareTo(key) > 0) {
                ranked[j + 1] = ranked[j];
                j--;
            }
            ranked[j + 1] = key;
        }
        return ranked;
    }
    public static void main(String[] args) {
        BusRoute[] routes = {
                new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4),
                new BusRoute("RT299T", "Night Service")
        };
        BusRoute[] ranked = rankRoutes(routes);
        System.out.print("[");
        for (int i = 0; i < ranked.length; i++) {
            System.out.print("\"" + ranked[i].getRouteCode() + "\"");
            if (i < ranked.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}