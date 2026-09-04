import java.util.Scanner;
public class BankReferenceValidator {
    static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed;
        }
        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return bankCode + rest;
    }
    static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: reference must be exactly 14 characters";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: reference body must be digits";
            }
        }
        String bankCode = reference.substring(0, 3);
        String day = reference.substring(3, 5);
        String month = reference.substring(5, 7);
        String year = reference.substring(7, 9);
        String sequence = reference.substring(9, 14);
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ")
                .append(day).append("/").append(month).append("/").append(year)
                .append(" | SEQ: ").append(sequence);
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter raw reference: ");
        String raw = sc.nextLine();
        String normalized = normalizeReference(raw);
        String result = validateAndFormat(normalized);
        System.out.println(result);
        sc.close();
    }
}