
import java.util.*;

public class RecentPagesCache {

    static class Node {
        int key;
        String value;
        Node prev, next;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    static class RecentCache {
        int capacity;
        HashMap<Integer, Node> map;
        Node head, tail;

        RecentCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();

            head = new Node(0, "");
            tail = new Node(0, "");

            head.next = tail;
            tail.prev = head;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        void insert(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        String get(int key) {
            if (!map.containsKey(key))
                return "-1";

            Node node = map.get(key);

            remove(node);
            insert(node);

            return node.value;
        }

        String put(int key, String value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;

                remove(node);
                insert(node);

                return "updated";
            }

            String result = "";

            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);

                result = " evicted " + lru.key;
            }

            Node node = new Node(key, value);
            insert(node);
            map.put(key, node);

            return result;
        }

        void display() {
            Node temp = head.next;

            System.out.print("Cache (most recent first): ");

            while (temp != tail) {
                System.out.print(temp.key + "=" + temp.value);

                temp = temp.next;

                if (temp != tail)
                    System.out.print(", ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int capacity = sc.nextInt();
        int n = sc.nextInt();

        RecentCache cache = new RecentCache(capacity);

        for (int i = 0; i < n; i++) {
            String operation = sc.next();

            if (operation.equals("put")) {
                int key = sc.nextInt();
                String value = sc.next();

                String result = cache.put(key, value);

                System.out.println("put(" + key + "," + value + ")" + result);

            } else if (operation.equals("get")) {
                int key = sc.nextInt();

                String result = cache.get(key);

                System.out.println("get(" + key + ") -> " + result);
            }
        }

        cache.display();
        sc.close();
    }
}
