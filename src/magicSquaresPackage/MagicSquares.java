package magicSquaresPackage;

import java.util.Scanner;

/*
 * Class Name: MagicSquares
 * Date: 2019-10-29
 * Author: Daniel DeAnda
 *
 * Class Purpose
 *
 *
 * Class Design Description
 *
 *
 * Inputs:
 * None
 *
 * Outputs:
 * Console outputs (for displaying potential magic squares and reporting on their validity in a formatted way).
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
     *
     *
     * Method Design Description
     *
     *
     * Inputs:
     * None
     *
     * Outputs:
     * None
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
     * Manages and performs the operations outlined in the class comment on line 10 by performing general
     * control and conditional logic, managing variables, displaying relevant information, and calling the
     * methods above.
     *
     * Method Design Description
     * Establishes a system of input and obtains initial stake and bet values from the user, then
     * runs games of Craps as long as both the user's bet value is nonzero and the user's stake value
     * is greater than zero through the use of a while loop, obtaining a new bet value from the user
     * after each round as necessary.  Conditional structures are used in order to determine victory/loss
     * and what subsequent actions should be taken.
     *
     * Inputs:
     * args - the String array of command-line arguments (not used in this program).
     *
     * Outputs:
     * Console outputs (for the current game state and game statistics).
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