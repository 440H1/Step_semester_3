
import java.util.Stack;

public class HackathonBannerPrinter {

    public static String expandBanner(String code) {

        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> textStack = new Stack<>();

        StringBuilder current = new StringBuilder();
        int count = 0;

        for (int i = 0; i < code.length(); i++) {

            char ch = code.charAt(i);

            if (Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
            }

            else if (ch == '[') {
                countStack.push(count);
                textStack.push(current);

                current = new StringBuilder();
                count = 0;
            }

            else if (ch == ']') {
                int repeat = countStack.pop();
                StringBuilder previous = textStack.pop();

                for (int j = 0; j < repeat; j++) {
                    previous.append(current);
                }

                current = previous;
            }

            else {
                current.append(ch);
            }
        }

        return current.toString();
    }

    public static void main(String[] args) {

        String code1 = "3[ab]2[c]";
        String code2 = "2[x3[y]]z";

        System.out.println(expandBanner(code1));
        System.out.println(expandBanner(code2));
    }
}
