import java.util.Scanner;

class LibraryISBNNormalizer {
    String normalizeCode(String raw) {
        String c = raw.trim();
        String pub = c.substring(0, 3).toUpperCase();
        return pub + c.substring(3);
    }
    void validateAndFormat(String code) {
        if (code.length() != 13) {
            System.out.println("Invalid: wrong length");
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                System.out.println("Invalid: publisher code must be 3 letters");
                return;
            }
        }
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                System.out.println("Invalid: body must be digits");
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(code.substring(0, 3)).append("] YEAR: ")
                .append(code.substring(3, 7)).append(" | CATALOG: ")
                .append(code.substring(7));
        System.out.println(sb.toString());
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String raw = sc.nextLine();
        LibraryISBNNormalizer obj = new LibraryISBNNormalizer();
        String norm = obj.normalizeCode(raw);
        obj.validateAndFormat(norm);
    }
}