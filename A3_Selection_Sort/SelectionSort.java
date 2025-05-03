import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelectionSort {

    private static List<Double> numbers = new ArrayList<>();

    // Function to take input for numbers
    public static void inputNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many numbers do you wish to enter?\nTotal numbers: ");
        int total = scanner.nextInt();
        for (int i = 1; i <= total; i++) {
            System.out.print("Enter number " + i + ": ");
            double numIn = scanner.nextDouble();
            numbers.add(numIn);
        }
        System.out.println("The numbers you've entered are: " + numbers);
    }

    // Function for selection sort
    public static void selectionSort() {
        for (int i = 0; i < numbers.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(j) < numbers.get(minIndex)) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            double temp = numbers.get(minIndex);
            numbers.set(minIndex, numbers.get(i));
            numbers.set(i, temp);
        }
        System.out.println("Numbers sorted in ascending order using selection sort: " + numbers);
    }

    public static void chooseOption() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option from the menu below:");
            System.out.println("1 -> Input numbers");
            System.out.println("2 -> Apply Selection Sorting");
            System.out.println("3 -> Exit");
            System.out.print("Choose an option (1-3): ");
            int option = scanner.nextInt();

            if (option == 1) {
                inputNumbers();
            } else if (option == 2) {
                selectionSort();
            } else if (option == 3) {
                System.out.println("\n## END OF CODE\n");
                break; // Exit the loop
            } else {
                System.out.println("\nPlease choose a valid option (1-3).\n");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        chooseOption();
    }
}
