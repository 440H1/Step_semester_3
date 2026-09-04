import java.util.Scanner;
public class MaskedPhoneNumberFormatter {
    static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }
        String lastFour = phone.substring(phone.length() - 4);
        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX");
        sb.insert(6, "-");
        sb.append(lastFour);
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();
        String result = maskPhoneNumber(phone);
        System.out.println(result);
        sc.close();
    }
}