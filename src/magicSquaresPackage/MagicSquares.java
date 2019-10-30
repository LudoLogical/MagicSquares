package magicSquaresPackage;

import java.util.Scanner;

/*
 * Class Name: MagicSquares
 * Date: 2019-10-29
 * Author: Daniel DeAnda
 *
 * Class Purpose
 * Manages the evaluation of the potential magic squares in the magic.txt input file and reports on
 * their validity by calling other methods and relaying the results to the user through the console.
 *
 * Class Design Description
 * Controls overall program execution by holding the screen for user input, displaying the results of the
 * analysis of each potential magic square in the magic.txt input file performed by a MagicSquareValidator,
 * and reporting that this process is finished when the entire file has been examined as described in the
 * method comments for the methods contained in this class.
 *
 * Inputs:
 * None
 *
 * Outputs:
 * Console outputs (for displaying information about each of the potential magic squares in magic.txt
 * in a formatted way and reporting the completion of this process).
 *
 * Returns:
 * N/A
 */
public class MagicSquares {

    // This constant ensures that the file path on line xx is platform-agnostic
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    // This Scanner exists specifically to listen for user input before continuing
    public static Scanner keyboard = new Scanner(System.in);

    /*
     * Method Name: waitForUserInput (static)
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Pauses the execution of this program until the user provides input in the
     * form of a press of the enter key.
     *
     * Method Design Description
     * Uses the nextLine() function of a Scanner linked to the keyboard in order to
     * wait for the user to press the enter key.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * A single console output (for prompting the user for acknowledgement)
     *
     * Returns:
     * Nothing
     */
    public static void waitForUserInput() {
        System.out.print("Press any key to continue . . .");
        keyboard.nextLine();
        keyboard.reset();
    }

    /*
     * Method Name: main
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Manages the evaluation of the potential magic squares in the magic.txt input file and reports on
     * their validity by calling other methods and relaying the results to the user through the console.
     *
     * Method Design Description
     * Instantiates a MagicSquareValidator with magic.txt as its input file, then queries it in the
     * appropriate manner and order for information concerning its progress and the validity of each
     * potential magic square.  Results are printed to the console until the entire file has been checked,
     * at which point the program reports that this is the case and terminates.
     *
     * Inputs:
     * args - the String array of command-line arguments (not used in this program).
     *
     * Outputs:
     * Console outputs (for indicating which potential magic squares from magic.txt are indeed magic squares,
     * separating each evaluation, and reporting completion), as well as those of called methods.
     *
     * Returns:
     * Nothing
     */
    public static void main(String[] args) {

        String path = "src" + FILE_SEPARATOR + "magicSquaresPackage" + FILE_SEPARATOR + "magic.txt";
        MagicSquareValidator validator = new MagicSquareValidator(path);
        String separator = "--------------------------------------------"; // Indicates a new "screen"

        ;
        boolean openSuccessful = validator.canRead();
        if (openSuccessful) {

            do {

                System.out.println(separator);

                boolean atEndOfFile = validator.loadNextSquare();
                if (atEndOfFile) {
                    break;
                }

                validator.printCurrentSquare();
                System.out.println(); // for formatting consistency

                if (validator.isMagicSquare()) {
                    System.out.print("A " + validator.getCurrentSquareSize() + " Magic Square ");
                    System.out.println("with Magic Sum " + validator.getCurrentMagicSum());
                } else {
                    System.out.println("Not a Magic Square");
                }

                waitForUserInput();

            } while (true);

            // Informs the user that the program has examined the entire file;
            // extras for formatting consistency
            System.out.println("End of File");
            waitForUserInput();
            System.out.println(separator);
            System.out.println("Th-Th-That's All Folks...");
            waitForUserInput();
            keyboard.close();

        } else {
            waitForUserInput();
        }

    }

}