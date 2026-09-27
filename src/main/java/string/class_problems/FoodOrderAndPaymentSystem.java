import java.util.ArrayList;
public class FoodOrderAndPaymentSystem {

    interface IPaymentMethod {
        boolean processPayment(double amount);
        String getPaymentMethodName();
    }

    class CreditCardPayment implements IPaymentMethod {

        public boolean processPayment(double amount) {
            return true;
        }

        public String getPaymentMethodName() {
            return "Credit Card";
        }
    }

    class DigitalWalletPayment implements IPaymentMethod {

        public boolean processPayment(double amount) {
            return false;
        }

        public String getPaymentMethodName() {
            return "Digital Wallet";
        }
    }

    class CashOnDeliveryPayment implements IPaymentMethod {

        public boolean processPayment(double amount) {
            return true;
        }

        public String getPaymentMethodName() {
            return "Cash on Delivery";
        }
    }

    class Restaurant {
        String restaurantName;

        Restaurant(String restaurantName) {
            this.restaurantName = restaurantName;
        }
    }

    class FoodItem {
        String itemName;
        double price;
        Restaurant restaurant;

        FoodItem(String itemName, double price, Restaurant restaurant) {
            this.itemName = itemName;
            this.price = price;
            this.restaurant = restaurant;
        }
    }

    class LineItem {
        FoodItem foodItem;
        int quantity;

        LineItem(FoodItem foodItem, int quantity) {
            this.foodItem = foodItem;
            this.quantity = quantity;
        }

        double getTotalPrice() {
            return foodItem.price * quantity;
        }
    }

    class Order {
        int orderNumber;
        ArrayList<LineItem> items = new ArrayList<>();
        String status = "Pending Payment";

        Order(int orderNumber) {
            this.orderNumber = orderNumber;
            System.out.println("Order created");
        }

        void addItem(FoodItem foodItem, int quantity) {
            items.add(new LineItem(foodItem, quantity));

            System.out.println(
                    "Added " +
                            foodItem.itemName +
                            " (Qty " +
                            quantity +
                            ")"
            );
        }

        boolean placeOrder() {
            if (items.isEmpty()) {
                System.out.println(
                        "Cannot place order: Order must contain at least one item."
                );
                return false;
            }

            System.out.println("Order placed successfully.");
            return true;
        }

        double calculateTotal() {
            double total = 0;

            for (LineItem item : items) {
                total += item.getTotalPrice();
            }

            return total;
        }

        void makePayment(IPaymentMethod paymentMethod) {

            boolean successful =
                    paymentMethod.processPayment(calculateTotal());

            if (successful) {
                status = "Paid";

                System.out.println(
                        "Payment via " +
                                paymentMethod.getPaymentMethodName() +
                                " successful."
                );

                System.out.println(
                        "Order status: " + status
                );

                System.out.println(
                        "Notification: Order #" +
                                orderNumber +
                                " placed and paid."
                );

            } else {

                System.out.println(
                        "Payment via " +
                                paymentMethod.getPaymentMethodName() +
                                " failed."
                );

                System.out.println(
                        "Order status: " + status
                );

                System.out.println(
                        "Notification: Order #" +
                                orderNumber +
                                " placed, awaiting payment."
                );
            }
        }
    }

    class Customer {
        String customerName;

        Customer(String customerName) {
            this.customerName = customerName;
        }

        Order createOrder(int orderNumber) {
            return new Order(orderNumber);
        }
    }

    public static void main(String[] args) {

        FoodOrderAndPaymentSystem system =
                new FoodOrderAndPaymentSystem();

        Customer customer =
                system.new Customer("Arjun");

        Restaurant restaurant =
                system.new Restaurant("Food Corner");

        FoodItem pizza =
                system.new FoodItem(
                        "Pizza",
                        200,
                        restaurant
                );

        FoodItem soda =
                system.new FoodItem(
                        "Soda",
                        50,
                        restaurant
                );

        FoodItem burger =
                system.new FoodItem(
                        "Burger",
                        150,
                        restaurant
                );

        IPaymentMethod creditCard =
                system.new CreditCardPayment();

        IPaymentMethod digitalWallet =
                system.new DigitalWalletPayment();

        Order emptyOrder =
                customer.createOrder(122);

        emptyOrder.placeOrder();

        Order firstOrder =
                customer.createOrder(123);

        firstOrder.addItem(pizza, 2);
        firstOrder.addItem(soda, 1);

        firstOrder.placeOrder();
        firstOrder.makePayment(creditCard);

        Order secondOrder =
                customer.createOrder(124);

        secondOrder.addItem(burger, 1);

        secondOrder.placeOrder();
        secondOrder.makePayment(digitalWallet);
    }
}