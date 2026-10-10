class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class OrderManagementSystem {

    public static Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node lead = dummy;
        Node trailing = dummy;

        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        while (lead != null) {
            lead = lead.next;
            trailing = trailing.next;
        }

        trailing.next = trailing.next.next;

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
        Node head1 = new Node(10);
        head1.next = new Node(20);
        head1.next.next = new Node(30);
        head1.next.next.next = new Node(40);
        head1.next.next.next.next = new Node(50);

        head1 = removeKthFromEnd(head1, 2);
        printList(head1);

        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);
        head2.next.next.next = new Node(40);
        head2.next.next.next.next = new Node(50);

        head2 = removeKthFromEnd(head2, 5);
        printList(head2);
    }
}