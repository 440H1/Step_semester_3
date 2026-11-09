
import java.util.Stack;

public class QuizScoringFormula {

    public static int evaluate(String expr) {

        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {

            char ch = expr.charAt(i);

            if (ch == ' ') {
                continue;
            }

            // Read multi-digit numbers
            if (Character.isDigit(ch)) {
                int num = 0;

                while (i < expr.length() &&
                        Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }

                i--;
                values.push(num);
            }

            // Opening bracket
            else if (ch == '(') {
                operators.push(ch);
            }

            // Closing bracket
            else if (ch == ')') {
                while (operators.peek() != '(') {
                    calculate(values, operators);
                }

                operators.pop();
            }

            // Operators
            else {
                while (!operators.isEmpty() &&
                        operators.peek() != '(' &&
                        precedence(operators.peek()) >= precedence(ch)) {
                    calculate(values, operators);
                }

                operators.push(ch);
            }
        }

        // Process remaining operators
        while (!operators.isEmpty()) {
            calculate(values, operators);
        }

        return values.pop();
    }

    // Operator precedence
    public static int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }

        if (op == '*' || op == '/') {
            return 2;
        }

        return 0;
    }

    // Perform calculation
    public static void calculate(Stack<Integer> values,
                                 Stack<Character> operators) {

        int b = values.pop();
        int a = values.pop();
        char op = operators.pop();

        switch (op) {
            case '+':
                values.push(a + b);
                break;

            case '-':
                values.push(a - b);
                break;

            case '*':
                values.push(a * b);
                break;

            case '/':
                values.push(a / b);
                break;
        }
    }

    public static void main(String[] args) {

        String expr1 = "2 * (3 + 4) - 10 / 5";
        String expr2 = "(8 - 2) * (1 + 2) / 4";

        System.out.println(evaluate(expr1));
        System.out.println(evaluate(expr2));
    }
}
