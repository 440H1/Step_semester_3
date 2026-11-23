
import java.util.*;

public class ExamScriptbatches {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    static Node reverseInGroups(Node head, int k) {
        Node current = head;
        Node newHead = null;
        Node previousGroupTail = null;

        while (current != null) {
            Node check = current;
            int count = 0;

            while (check != null && count < k) {
                check = check.next;
                count++;
            }

            if (count < k) {
                if (previousGroupTail != null)
                    previousGroupTail.next = current;
                break;
            }

            Node groupTail = current;
            Node prev = null;

            for (int i = 0; i < k; i++) {
                Node nextNode = current.next;
                current.next = prev;
                prev = current;
                current = nextNode;
            }

            if (newHead == null)
                newHead = prev;
            else
                previousGroupTail.next = prev;

            previousGroupTail = groupTail;
        }

        return newHead != null ? newHead : head;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {
            Node newNode = new Node(sc.nextInt());

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int k = sc.nextInt();

        head = reverseInGroups(head, k);

        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
        sc.close();
    }
}
