import java.util.Scanner;
class PINLengthValidator {
    void checkPinLength(String pin) {
        if (pin.length() == 4) {
            System.out.println("PIN length OK.");
        } else {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pin = scanner.nextLine();
        PINLengthValidator validator = new PINLengthValidator();
        validator.checkPinLength(pin);
    }
}