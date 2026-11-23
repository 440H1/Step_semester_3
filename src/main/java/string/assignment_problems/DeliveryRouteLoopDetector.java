
import java.util.*;

public class DeliveryRouteLoopDetector {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    static void detectLoop(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                int length = 1;
                Node temp = slow.next;

                while (temp != slow) {
                    length++;
                    temp = temp.next;
                }

                Node ptr1 = head;
                Node ptr2 = slow;

                while (ptr1 != ptr2) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }

                System.out.println("Loop: yes, starts at stop " + ptr1.data + ", length " + length);
                return;
            }
        }

        System.out.println("Loop: no");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int pos = sc.nextInt();

        if (pos >= 0 && pos < n) {
            Node temp = head;

            for (int i = 0; i < pos; i++) {
                temp = temp.next;
            }

            tail.next = temp;
        }

        detectLoop(head);
        sc.close();
    }
}
