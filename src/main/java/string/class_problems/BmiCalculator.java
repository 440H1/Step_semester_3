import java.util.Scanner;
public class BmiCalculator {
    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25.0) return "Normal";
        else if (bmi < 30.0) return "Overweight";
        else return "Obese";
    }
    static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI | Status");
        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            System.out.printf("Person %d — Height: %.2f m, Weight: %.2f kg | BMI: %.2f | Status: %s%n",
                    (i + 1), heights[i], weights[i], bmi, getBmiStatus(bmi));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 10;

        double[] heights = new double[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter height (m) for Person " + (i + 1) + ": ");
            heights[i] = sc.nextDouble();
            System.out.print("Enter weight (kg) for Person " + (i + 1) + ": ");
            weights[i] = sc.nextDouble();
        }
        printWellnessReport(heights, weights);
        sc.close();
    }
}