import java.util.*;

public class CampusCanteenSmartCard {

    interface PricingPlan {
        double applyDiscount(double originalPrice);
    }

    static class DayScholarPlan implements PricingPlan {
        @Override
        public double applyDiscount(double originalPrice) {
            return originalPrice;
        }
    }

    static class HostellerPlan implements PricingPlan {
        @Override
        public double applyDiscount(double originalPrice) {
            return originalPrice * 0.90;
        }
    }

    static class StaffPlan implements PricingPlan {
        @Override
        public double applyDiscount(double originalPrice) {
            return originalPrice * 0.80;
        }
    }

    static class Transaction {
        private String description;
        private double amount;
        private boolean isRefunded;

        public Transaction(String description, double amount) {
            this.description = description;
            this.amount = amount;
            this.isRefunded = false;
        }

        public String getDescription() {
            return description;
        }

        public double getAmount() {
            return amount;
        }

        public boolean isRefunded() {
            return isRefunded;
        }

        public void setRefunded(boolean refunded) {
            isRefunded = refunded;
        }
    }

    static class SmartCard {
        private String cardId;
        private PricingPlan plan;
        private double balance;
        private boolean isBlocked;
        private List<Transaction> transactions = new ArrayList<>();

        public SmartCard(String cardId, PricingPlan plan) {
            this.cardId = cardId;
            this.plan = plan;
            this.balance = 0.0;
            this.isBlocked = false;
        }

        public String getCardId() {
            return cardId;
        }

        public void topUp(double amount) {
            if (isBlocked) {
                System.out.println("Top-up failed: Card " + cardId + " is blocked.");
                return;
            }
            if (amount < 100) {
                System.out.println("Top-up failed: Minimum top-up amount is ₹100.");
                return;
            }
            if (balance + amount > 5000) {
                System.out.println("Top-up failed: Balance cannot exceed ₹5,000.");
                return;
            }

            balance += amount;
            transactions.add(new Transaction("Top-up", amount));
            System.out.println(cardId + " topped up with ₹" + String.format(Locale.US, "%.2f", amount) +
                    ". Balance: ₹" + String.format(Locale.US, "%.2f", balance) + ".");
        }

        public void purchase(String itemName, double originalPrice) {
            if (isBlocked) {
                System.out.println("Purchase failed: Card " + cardId + " is blocked.");
                return;
            }

            double finalPrice = plan.applyDiscount(originalPrice);
            if (balance < finalPrice) {
                System.out.println("Purchase failed: Insufficient balance (required ₹" +
                        String.format(Locale.US, "%.2f", finalPrice) + ", available ₹" +
                        String.format(Locale.US, "%.2f", balance) + ").");
                return;
            }

            balance -= finalPrice;
            transactions.add(new Transaction(itemName, -finalPrice));
            System.out.println(itemName + " purchased for ₹" + String.format(Locale.US, "%.2f", finalPrice) +
                    ". Balance: ₹" + String.format(Locale.US, "%.2f", balance) + ".");
        }

        public void refund(String itemName) {
            if (isBlocked) {
                System.out.println("Refund failed: Card " + cardId + " is blocked.");
                return;
            }

            Transaction targetTx = null;
            for (int i = transactions.size() - 1; i >= 0; i--) {
                Transaction tx = transactions.get(i);
                if (tx.getDescription().equals(itemName)) {
                    targetTx = tx;
                    break;
                }
            }

            if (targetTx == null) {
                System.out.println("Refund rejected: No purchase found for " + itemName + ".");
                return;
            }

            if (targetTx.isRefunded()) {
                System.out.println("Refund rejected: " + itemName + " has already been refunded.");
                return;
            }

            double refundAmount = Math.abs(targetTx.getAmount());
            targetTx.setRefunded(true);
            balance += refundAmount;
            transactions.add(new Transaction("Refund: " + itemName, refundAmount));

            System.out.println("Refund of ₹" + String.format(Locale.US, "%.2f", refundAmount) +
                    " for " + itemName + " processed. Balance: ₹" + String.format(Locale.US, "%.2f", balance) + ".");
        }

        public void printMiniStatement() {
            StringBuilder sb = new StringBuilder("Mini-statement for " + cardId + ": ");
            for (int i = 0; i < transactions.size(); i++) {
                double amt = transactions.get(i).getAmount();
                if (i > 0) sb.append(", ");
                if (amt > 0) {
                    sb.append("+").append(String.format(Locale.US, "%.2f", amt));
                } else {
                    sb.append(String.format(Locale.US, "%.2f", amt));
                }
            }
            sb.append(" = ₹").append(String.format(Locale.US, "%.2f", balance));
            System.out.println(sb.toString());
        }

        public void setBlocked(boolean blocked) {
            this.isBlocked = blocked;
        }
    }

    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());

        card.topUp(500);
        card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Bulk Order", 400);

        card.refund("Veg Thali");
        card.refund("Veg Thali");

        card.printMiniStatement();
    }
}