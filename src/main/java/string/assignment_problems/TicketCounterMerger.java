public class TicketCounterMerger {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node mergeSorted(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }

        return dummy.next;
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node a = new Node(3);
        a.next = new Node(8);
        a.next.next = new Node(15);

        Node b = new Node(5);
        b.next = new Node(8);
        b.next.next = new Node(12);
        b.next.next.next = new Node(20);

        Node merged1 = mergeSorted(a, b);
        printList(merged1);

        Node emptyA = null;
        Node singleB = new Node(4);

        Node merged2 = mergeSorted(emptyA, singleB);
        printList(merged2);
    }
}