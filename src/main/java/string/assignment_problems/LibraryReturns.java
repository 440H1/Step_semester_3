
import java.util.*;

public class LibraryReturns {

    static class Node {
        String title;
        int day;
        Node next;

        Node(String title, int day) {
            this.title = title;
            this.day = day;
            next = null;
        }
    }

    static Node sortByDueDay(Node head) {
        if (head == null || head.next == null)
            return head;

        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node second = slow.next;
        slow.next = null;

        Node left = sortByDueDay(head);
        Node right = sortByDueDay(second);

        return merge(left, right);
    }

    static Node merge(Node left, Node right) {
        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (left != null && right != null) {
            if (left.day <= right.day) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }

            tail = tail.next;
        }

        if (left != null)
            tail.next = left;

        if (right != null)
            tail.next = right;

        return dummy.next;
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.title + ":" + head.day + " -> ");
            head = head.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {
            String title = sc.next();
            int day = sc.nextInt();

            Node newNode = new Node(title, day);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        head = sortByDueDay(head);

        display(head);

        sc.close();
    }
}
