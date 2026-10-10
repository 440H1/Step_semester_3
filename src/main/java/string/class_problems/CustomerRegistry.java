import java.util.*;

class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class CustomerRegistry {
    private Set<Customer> registry = new HashSet<>();

    public boolean addCustomer(Customer customer) {
        boolean added = registry.add(customer);
        if (added) {
            System.out.println("true");
        } else {
            System.out.println("false (duplicate rejected)");
        }
        return added;
    }

    public boolean contains(Customer customer) {
        return registry.contains(customer);
    }

    public int getUniqueCount() {
        return registry.size();
    }

    public static void main(String[] args) {
        CustomerRegistry system = new CustomerRegistry();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        system.addCustomer(c1);
        system.addCustomer(c2);
        system.addCustomer(c3);

        System.out.println("unique count " + system.getUniqueCount());
        System.out.println("contains: " + system.contains(new Customer(101, "Asha")));
    }
}