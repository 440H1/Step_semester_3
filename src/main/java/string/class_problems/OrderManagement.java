import java.util.Scanner;

public class OrderManagement {

    static class Product {
        String productName;
        int quantity;
        double unitPrice;

        Product(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        double total() {
            return quantity * unitPrice;
        }
    }

    Product[] products;
    int count;

    OrderManagement(int size) {
        products = new Product[size];
        count = 0;
    }

    void addProduct(String name, int quantity, double price) {
        products[count] = new Product(name, quantity, price);
        count++;
    }

    class OrderCursor {
        int index = 0;

        boolean hasNext() {
            return index < count;
        }

        Product next() {
            return products[index++];
        }
    }

    void displayOrder() {
        OrderCursor cursor = new OrderCursor();
        double orderTotal = 0;

        while (cursor.hasNext()) {
            Product p = cursor.next();

            System.out.println(p.productName + " " + p.total());
            orderTotal += p.total();
        }

        System.out.println("Total " + orderTotal);
        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("product: static nested (needs no order)");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        OrderManagement order = new OrderManagement(n);

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int quantity = sc.nextInt();
            double price = sc.nextDouble();

            order.addProduct(name, quantity, price);
        }

        order.displayOrder();
        sc.close();
    }
}
