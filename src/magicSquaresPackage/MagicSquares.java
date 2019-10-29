package magicSquaresPackage;

import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class MagicSquares {

    // This constant ensures that the file path on line xx is platform-agnostic
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    // This Scanner exists specifically to listen for user input before continuing
    public static Scanner keyboard = new Scanner(System.in);

    public static void waitForUserInput() {
        System.out.print("Press any key to continue . . .");
        keyboard.nextLine();
        keyboard.reset();
    }

    public static void main(String[] args) {

        MagicSquareValidator validator = new MagicSquareValidator();
        String separator = "--------------------------------------------"; // Indicates a new "screen"

        String path = "src" + FILE_SEPARATOR + "magicSquaresPackage" + FILE_SEPARATOR + "magic.txt";
        boolean openSuccessful = validator.openFile(path);
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

        }

    }

}