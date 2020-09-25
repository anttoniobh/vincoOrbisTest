import java.util.Scanner;

/**
 * @author Antonio Bonifacio Hernández
 */

public class GardenSquares {

    public static void main(String[] args) {
        char[][][] input = getInput();
        // displayInput(input);

        if (isInputValid(input)) {
            int T = input.length;
            int[] output = new int[T];

            // run test cases
            for (int i = 0; i < T; i++) {
                output[i] = 0;
                int N = input[i].length;    // rows
                int M = input[i][0].length; // columns

                // run square heights
                for (int height = 2; height <= N; height++) {
                    // run square widths
                    for (int width = 2; width <= M; width++) {
                        int x, y;

                        // run garden squares
                        for (int j = 0; j < N; j++) {
                            y = j + height - 1;
                            if (y >= M)
                                break;
                            for (int k = 0; k < M; k++) {
                                x = k + width - 1;
                                if (x >= N)
                                    break;
                                // System.out.println(i + ": " + j + "," + k);
                                // System.out.print(input[i][j][k]); System.out.print(input[i][j][x]); System.out.println();
                                // System.out.print(input[i][y][k]); System.out.print(input[i][y][x]); System.out.println();
                                if (input[i][j][k] == input[i][j][x] && input[i][j][x] == input[i][y][x] && input[i][y][x] == input[i][y][k])
                                    output[i]++;
                            }
                        }
                    }
                }

            }

            displayOutput(output);
        }

    }//

    /**
     * Receives data from user, as the problem definition states
     *
     * @return A matrix containing the number of test cases (an integer), the size of each square and the
     * garden squares for each test case (as string lines)
     */
    public static char[][][] getInput() {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        scan.nextLine();
        char[][][] input = new char[T][][];
        for (int i = 0; i < T; i++) {
            String[] matrixSize = scan.nextLine().trim().replaceAll("\\s+", " ").split(" ");
            int N = Integer.parseInt(matrixSize[0]);
            int M = Integer.parseInt(matrixSize[1]);
            input[i] = new char[N][M];
            for (int j = 0; j < N; j++) {
                String line = scan.nextLine().trim().replaceAll("\\s+", " ");
                for (int k = 0; k < M; k++)
                    input[i][j][k] = line.charAt(k);
            }
        }
        return input;
    }

    /**
     * Displays input data
     *
     * @param input A 3d array with char values, containing garden squares
     */
    public static void displayInput(char[][][] input) {
        System.out.println();
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i].length + " " + input[i][0].length);
            for (int j = 0; j < input[i].length; j++) {
                for (int k = 0; k < input[i][j].length; k++)
                    System.out.print(input[i][j][k] + " ");
                System.out.println();
            }
        }
    }

    /**
     * Displays output data
     *
     * @param output Square garden counting by test case
     */
    public static void displayOutput(int[] output) {
        System.out.println();
        for (int i = 0; i < output.length; i++)
            System.out.println(output[i]);
    }

    /**
     * Validates conditions:
     * 1 <= T <= 50     (number of test cases)
     * 2 <= N, M <= 50  (size of a garden square)
     *
     * @param input Number of test cases, size of squares and garden squares as 2d char arrays
     * @return True if conditions are met
     */
    public static boolean isInputValid(char[][][] input) {
        int T = input.length;
        // validate test cases
        if (T < 1 || T > 50) {
            System.out.println("Invalid number of test cases !");
            return false;
        }
        // validate size of garden squares
        for (int i = 0; i < T; i++) {
            int N = input[i].length;
            int M = input[i][0].length;
            if (N < 2 || N > 50 || M < 2 || M > 50) {
                System.out.println("Invalid size for the square garden: " + (i + 1));
                return false;
            }
        }
        return true;
    }

}//
