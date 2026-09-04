public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean delivered;
    public FoodOrder(String studentName, String dishName) {
        if (!isValidField(studentName)) {
            throw new IllegalArgumentException("Invalid student name");
        }
        if (!isValidField(dishName)) {
            throw new IllegalArgumentException("Invalid dish name");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }
    private static boolean isValidField(String field) {
        if (field == null) {
            return false;
        }
        return !field.trim().isEmpty();
    }
    void markDelivered() {
        if (delivered) {
            System.out.println(studentName + "'s order was already marked delivered");
            return;
        }
        delivered = true;
        System.out.println(studentName + "'s order marked delivered");
    }
    static void processBatch(String[][] rawOrders) {
        int validCount = 0;
        int rejectedCount = 0;
        for (int i = 0; i < rawOrders.length; i++) {
            try {
                new FoodOrder(rawOrders[i][0], rawOrders[i][1]);
                validCount++;
            } catch (IllegalArgumentException e) {
                rejectedCount++;
            }
        }
        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount);
    }
    public static void main(String[] args) {
        String[][] rawOrders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", "   "},
                {"Divya", "Veg Biryani"}
        };
        processBatch(rawOrders);
    }
}