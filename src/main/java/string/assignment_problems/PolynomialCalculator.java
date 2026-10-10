public class PolynomialCalculator {

    static class Node {
        int coeff;
        int exp;
        Node next;

        Node(int coeff, int exp) {
            this.coeff = coeff;
            this.exp = exp;
            this.next = null;
        }
    }

    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exp > q.exp) {
                tail.next = new Node(p.coeff, p.exp);
                p = p.next;
                tail = tail.next;
            } else if (q.exp > p.exp) {
                tail.next = new Node(q.coeff, q.exp);
                q = q.next;
                tail = tail.next;
            } else {
                int sumCoeff = p.coeff + q.coeff;
                if (sumCoeff != 0) {
                    tail.next = new Node(sumCoeff, p.exp);
                    tail = tail.next;
                }
                p = p.next;
                q = q.next;
            }
        }

        while (p != null) {
            tail.next = new Node(p.coeff, p.exp);
            p = p.next;
            tail = tail.next;
        }

        while (q != null) {
            tail.next = new Node(q.coeff, q.exp);
            q = q.next;
            tail = tail.next;
        }

        return dummy.next;
    }

    public static void printPolynomial(Node head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        Node curr = head;

        while (curr != null) {
            int coeff = curr.coeff;
            int exp = curr.exp;

            if (curr != head) {
                if (coeff > 0) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                    coeff = Math.abs(coeff);
                }
            } else {
                if (coeff < 0) {
                    sb.append("-");
                    coeff = Math.abs(coeff);
                }
            }

            if (exp == 0) {
                sb.append(coeff);
            } else if (exp == 1) {
                if (coeff == 1) {
                    sb.append("x");
                } else {
                    sb.append(coeff).append("x");
                }
            } else {
                if (coeff == 1) {
                    sb.append("x^").append(exp);
                } else {
                    sb.append(coeff).append("x^").append(exp);
                }
            }

            curr = curr.next;
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Node p1 = new Node(5, 3);
        p1.next = new Node(4, 2);
        p1.next.next = new Node(2, 0);

        Node q1 = new Node(5, 2);
        q1.next = new Node(-3, 1);
        q1.next.next = new Node(1, 0);

        Node res1 = addPolynomials(p1, q1);
        printPolynomial(res1);

        Node p2 = new Node(3, 2);
        p2.next = new Node(1, 0);

        Node q2 = new Node(-3, 2);
        q2.next = new Node(4, 1);

        Node res2 = addPolynomials(p2, q2);
        printPolynomial(res2);
    }
}