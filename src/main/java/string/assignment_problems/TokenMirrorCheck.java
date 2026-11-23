
import java.util.*;

public class TokenMirrorCheck {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverse(slow.next);
        Node firstHalf = head;

        Node temp = secondHalf;
        boolean result = true;

        while (temp != null) {
            if (firstHalf.data != temp.data) {
                result = false;
                break;
            }

            firstHalf = firstHalf.next;
            temp = temp.next;
        }

        slow.next = reverse(secondHalf);

        return result;
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
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
            Node newNode = new Node(sc.nextInt());

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        boolean result = isPalindrome(head);

        System.out.println("Palindrome: " + result);
        System.out.print("List after check: ");
        display(head);

        sc.close();
    }
}
