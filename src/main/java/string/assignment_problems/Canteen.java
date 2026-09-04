public class Canteen implements Comparable<Canteen> {
    private final String canteenCode;
    private final String canteenName;
    private final int trustScore;
    private static final int DEFAULT_TRUST_SCORE = 3;
    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }
    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, DEFAULT_TRUST_SCORE);
    }
    @Override
    public int compareTo(Canteen other) {
        int scoreCompare = Integer.compare(other.trustScore, this.trustScore);
        if (scoreCompare != 0) {
            return scoreCompare;
        }
        int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeCompare != 0) {
            return codeCompare;
        }
        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }
    String getCanteenCode() {
        return canteenCode;
    }
    static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] ranked = canteens.clone();
        for (int i = 1; i < ranked.length; i++) {
            Canteen key = ranked[i];
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
        Canteen[] canteens = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats")
        };
        Canteen[] ranked = rankCanteens(canteens);
        System.out.print("[");
        for (int i = 0; i < ranked.length; i++) {
            System.out.print("\"" + ranked[i].getCanteenCode() + "\"");
            if (i < ranked.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}