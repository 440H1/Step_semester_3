import java.util.Scanner;
class CSVParser {
    void parseInventoryRecord(String line) {
        String[] f = line.split(",");
        if (f.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Product: " + f[0] + " | SKU: " + f[1] + " | Qty: " + f[2]);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        new CSVParser().parseInventoryRecord(line);
    }
}