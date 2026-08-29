import java.util.Scanner;
class WordReversalEncoder {
    String reverseEachWord(String s) {
        String[] w = s.split(" ");
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < w.length; i++) {
            r.append(new StringBuilder(w[i]).reverse());
            if (i < w.length - 1) r.append(" ");
        }
        return r.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(new WordReversalEncoder().reverseEachWord(s));
    }
}