
public class LinkedListIntersection {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static int length(Node head) {
        int count = 0;

        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    static Node findIntersection(Node a, Node b) {
        int lenA = length(a);
        int lenB = length(b);

        while (lenA > lenB) {
            a = a.next;
            lenA--;
        }

        while (lenB > lenA) {
            b = b.next;
            lenB--;
        }

        while (a != null && b != null) {
            if (a == b) {
                return a;
            }

            a = a.next;
            b = b.next;
        }

        return null;
    }

    public static void main(String[] args) {
        Node common = new Node(8);
        common.next = new Node(4);
        common.next.next = new Node(5);

        Node a = new Node(4);
        a.next = new Node(1);
        a.next.next = common;

        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(1);
        b.next.next.next = common;

        Node result = findIntersection(a, b);

        if (result != null) {
            System.out.println("Intersection at node: " + result.data);
        } else {
            System.out.println("No intersection");
        }
    }
}
