import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Antonio Bonifacio Hernández
 */

public class CuttingRecipies {

    public static void main(String[] args) {
        int[][] input = getInput();
        // displayData(input);

        if (isInputValid(input)) {
            int[][] output = new int[input.length][];
            List<Integer> primes = getPrimeNumbers();

            // run test cases
            for (int i = 0; i < input.length; i++) {
                int size = input[i].length;
                int maximum = getMaximumValue(input[i]);

                // 1 assign default values to output
                output[i] = new int[size];
                for (int j = 0; j < size; j++)
                    output[i][j] = input[i][j];

                // 2 pull out factors
                for (int prime : primes) {
                    if (prime > maximum)
                        break;

                    List<Integer> line;
                    boolean areAllDivisible;
                    do {
                        line = new ArrayList<>();
                        areAllDivisible = true;

                        for (int j = 0; j < size; j++) {
                            if (output[i][j] % prime == 0)
                                line.add(output[i][j] / prime);
                            else {
                                areAllDivisible = false;
                                break;
                            }
                        }

                        if (areAllDivisible)
                            for (int j = 0; j < size; j++)
                                output[i][j] = line.get(j);
                    } while (areAllDivisible);
                }
            }

            displayData(output);
        }

    }//

    /**
     * Receives data from user, as the problem definition states
     *
     * @return A matrix containing integers that represent test cases and number and quantity of ingredients
     */
    public static int[][] getInput() {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        int input[][] = new int[T][];
        for (int i = 0; i < T; i++) {
            int N = scan.nextInt();
            input[i] = new int[N];
            for (int j = 0; j < N; j++) {
                input[i][j] = scan.nextInt();
            }
        }
        return input;
    }

    /**
     * Displays either input or output data
     *
     * @param data A 2d array with integer values
     */
    public static void displayData(int[][] data) {
        System.out.println();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++)
                System.out.print(data[i][j] + " ");
            System.out.println();
        }
    }

    /**
     * Validates conditions:
     * T <= 100      (number of test cases)
     * 2 <= N <= 50  (number of ingredients per test case)
     *
     * @param input Number of test cases and number and quantity of ingredients as an integer matrix
     * @return True if conditions are met
     */
    public static boolean isInputValid(int[][] input) {
        // validate test cases
        if (input.length > 100) {
            System.out.println("Invalid number of test cases !");
            return false;
        }
        // validate number of ingredients
        for (int i = 0; i < input.length; i++) {
            if (input[i].length < 2 || input[i].length > 50) {
                System.out.println("Invalid number of ingredients at line (test case): " + (i + 1));
                return false;
            }
        }
        return true;
    }

    /**
     * Generates primes numbers between 2 and 50
     *
     * @return Prime numbers as a dynamic list
     */
    public static List<Integer> getPrimeNumbers() {
        List<Integer> primes = new ArrayList<>();
        for (int value = 2; value <= 50; value++) {
            boolean hasAnotherDivider = false;
            for (int prime : primes)
                if (hasAnotherDivider = value % prime == 0)
                    break;
            if (!hasAnotherDivider)
                primes.add(value);
        }
        return primes;
    }

    /**
     * Determines the maximum value in each test case received
     *
     * @param line Test case as an integer array
     * @return Maximum value. If not found (because an empty line), it returns -1
     */
    public static int getMaximumValue(int[] line) {
        int maximum = -1;
        for (int i = 0; i < line.length; i++)
            if (line[i] > maximum)
                maximum = line[i];
        return maximum;
    }

}//
