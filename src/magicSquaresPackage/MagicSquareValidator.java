package magicSquaresPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

/*
 * Class Name: MagicSquaresValidator
 * Date: 2019-10-29
 * Author: Daniel DeAnda
 *
 * Class Purpose
 * Examines a given input file of potential magic squares and determines whether or not
 * each of them is in fact a magic square.
 *
 * Class Design Description
 * Reads potential magic squares from a single file (whose path is specified during
 * construction and whose formatting is presumed to be correct) into a two-dimensional
 * Vector, reports on their characteristics, and determines their validity as described
 * in the method comments for the methods contained in this class.
 *
 * Inputs:
 * None
 *
 * Outputs:
 * Console outputs (for displaying error messages and issues with potential magic squares,
 * as well as reporting issues with those squares).
 *
 * Returns:
 * N/A
 */
public class MagicSquareValidator {

    private Scanner file;

    private Vector<Vector<Integer>> magicSquare;
    private int magicSquareSum;

    /*
     * Method Name: MagicSquareValidator (constructor)
     * Date: 2019-29-10
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Constructs a new MagicSquareValidator and returns it to the calling method.
     *
     * Method Design Description
     * Creates a new MagicSquareValidator and initializes its fields as applicable, then
     * attempts to load the file specified by String path and prints an error message if
     * unsuccessful.
     *
     * Inputs:
     * path - the location of the desired input file on the machine running the program
     *
     * Outputs:
     * A single console output (for error reporting) if and only if the input file was not
     * loaded successfully.
     *
     * Returns:
     * A new MagicSquareValidator with initialized fields.
     */
    public MagicSquareValidator(String path) {
        magicSquare = new Vector<>();
        magicSquareSum = 0;

        // Attempt to load input file
        try {
            FileReader reader = new FileReader(path);
            file = new Scanner(reader);
        } catch (FileNotFoundException e) { // an IOException
            System.out.println("ERROR: File " + path + " did not open successfully.");
            e.printStackTrace();
            file = null;
        }
    }


    /*
     * Method Name: getCurrentSquareSize
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Retrieves the size of the magic square currently being validated and returns it to the calling method.
     *
     * Method Design Description
     * Obtains the size of magicSquare by calling the appropriate method and returns the resulting value.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * None
     *
     * Returns:
     * The size of the magic square currently being validated by this MagicSquareValidator.
     */
    public int getCurrentSquareSize() {
        return magicSquare.size();
    }

    /*
     * Method Name: getCurrentMagicSum
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Retrieves the expected magic square sum of the magic square
     * currently being validated and returns it to the calling method.
     *
     * Method Design Description
     * Returns the value of magicSquareSum.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * None
     *
     * Returns:
     * the expected magic square sum of the magic square currently being validated.
     */
    public int getCurrentMagicSum() {
        return magicSquareSum;
    }

    /*
     * Method Name: canRead
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Determines whether or not this MagicSquareValidator was able to load its input file.
     *
     * Method Design Description
     * Checks the current value of Scanner file to determine whether or not loading was
     * successful.  If so, it will contain an object, but if not, it will contain null.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * None
     *
     * Returns:
     * True if the this MagicSquareValidator was able to correctly load the file
     * specified by the path it was provided when it was constructed, false otherwise.
     */
    public boolean canRead() {
        return file != null;
    }

    /*
     * Method Name: loadNextSquare
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Prepares this MagicSquareValidator to evaluate the next magic square in its
     * input file OR signals the calling method that this MagicSquareValidator has
     * run out of magic squares to check IF it has already examined the entire file.
     *
     * Method Design Description
     * Determines whether Scanner file contains unexamined data. If so, closes file,
     * but if not, reads the contents of the next magic square from the input file
     * into magicSquare (accounting for its size) and sets magicSquareSum accordingly.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * None
     *
     * Returns:
     * True if the this MagicSquareValidator prepared a new magic square from the
     * input file for evaluation, false if the entire file has already been examined.
     */
    public boolean loadNextSquare() {
        if (file.hasNextLine()) {

            int size = Integer.parseInt(file.nextLine());
            magicSquare.clear();
            magicSquare.setSize(size);
            magicSquareSum = (size*(size*size+1))/2;

            for (int row = 0; row < size; row++) {

                magicSquare.set(row, new Vector<>());
                magicSquare.get(row).setSize(size);
                String[] currentRow = file.nextLine().split(" ");

                for (int column = 0; column < size; column++) {
                    int currentValue = Integer.parseInt(currentRow[column]);
                    magicSquare.get(row).set(column, currentValue);
                }

            }

            return false;

        }
        file.close();
        return true;
    }

    /*
     * Method Name: printCurrentSquare
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Prints the magic square currently loaded within this MagicSquareValidator to the
     * console and normalizes each number within it such that they all have three digits
     * (i.e. 1 -> 001, 11 -> 011).
     *
     * Method Design Description
     * Cycles through each row (and then each column) of magicSquare, formatting each
     * element as specified above and then displaying each with a space between them
     * so that the output looks like a square.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * A single console output (for displaying the current magic square; formatted such
     * that all numbers have three digits in accordance with the published guidelines).
     *
     * Returns:
     * Nothing
     */
    public void printCurrentSquare() {

        for (int row = 0; row < magicSquare.size(); row++) {

            StringBuilder output = new StringBuilder(); // StringBuilders instead of Strings for efficiency

            for (int column = 0; column < magicSquare.size(); column++) {

                // Formats each number to have three digits (based on in-class direction and sample output)
                StringBuilder currentNumber = new StringBuilder(String.valueOf(magicSquare.get(row).get(column)));
                while (currentNumber.length() < 3) {
                    currentNumber.insert(0, "0");
                }
                output.append(currentNumber).append(" ");

            }

            // removes final trailing ' ' in output
            System.out.println(output.substring(0, output.length()-1));

        }

    }

    /*
     * Method Name: checkElements
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Determines whether or not the numbers in (i.e. elements of) the magic square
     * currently under consideration satisfy the requirements of a magic square.
     *
     * Method Design Description
     * Keeps track of the number of appearances of valid numbers in a Vector while
     * reporting all invalid ones to the user via the console, then reports any valid
     * numbers which appear an incorrect number of times (i.e. which appear more or
     * less than once) in the same manner.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * Console outputs (for displaying any and all issues encountered with the elements
     * present in or absent from the magic square currently under consideration by this
     * MagicSquareValidator).
     *
     * Returns:
     * True if there are no issues with the elements of the magic square currently loaded
     * into this MagicSquareValidator, false otherwise.
     */
    private boolean checkElements() {

        int size = magicSquare.size();
        Vector<Integer> numElements = new Vector<>();
        numElements.setSize(size*size+1); // elements range from 1-25; ignoring index 0
        for (int i = 1; i < numElements.size(); i++) {
            numElements.set(i, 0);
        }

        boolean isValid = true;

        for (int row = 0; row < magicSquare.size(); row++) {
            for (int column = 0; column < magicSquare.size(); column++) {

                int now = magicSquare.get(row).get(column);
                if (now >= 1 && now <= numElements.size()) {
                    numElements.set(now, numElements.get(now)+1);
                } else {
                    isValid = false;
                    System.out.print("The square has an entry " + now + " that is greater than ");
                    System.out.println(size + " squared which is " + (numElements.size()-1));
                    // subtract 1 because we don't use zero
                }

            }
        }

        for (int i = 1; i < numElements.size(); i++) {
            if (numElements.get(i) == 0) { // only possibility for less than 1
                isValid = false;
                System.out.println("The square does not have a " + i);
            } else if (numElements.get(i) > 1) {
                isValid = false;
                System.out.print("The square is not unique, it has ");
                System.out.println(numElements.get(i) + "elements with values of " + i);
            }
        }

        return isValid;

    }

    /*
     * Method Name: checkRowsAndColumns
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Determines whether or not the sums of the rows and columns of the magic square
     * currently under consideration by this MagicSquareValidator are equal to the
     * relevant magic square sum (and thus satisfy the requirements of a magic square).
     *
     * Method Design Description
     * Determines the sum of each row and column, one of each at a time, and reports
     * any rows and/or columns with component values that do not sum to the relevant
     * magic square sum to the user via the console.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * Console outputs (for displaying information concerning all instances where the sum
     * of a row or column of the magic square currently under consideration by this
     * MagicSquareValidator is not equal to the appropriate magic square sum, if any).
     *
     * Returns:
     * True if there are no issues with the sums of the rows and columns of the magic square
     * currently loaded into this MagicSquareValidator, false otherwise.
     */
    private boolean checkRowsAndColumns() {
        int rowSum, columnSum;
        boolean isValid = true;
        for (int i = 0; i < magicSquare.size(); i++) {
            rowSum = 0;
            columnSum = 0;
            for (int j = 0; j < magicSquare.size(); j++) {
                rowSum += magicSquare.get(i).get(j); // row = i, column = j
                columnSum += magicSquare.get(j).get(i); // column = i, row = j
            }
            if (rowSum != magicSquareSum) {
                isValid = false;
                System.out.print("Row #" + (i+1) + " does not produce the expected magic square sum ");
                System.out.println("for a " + magicSquare.size() + " magic square (" + magicSquareSum + ")");
            }
            if (columnSum != magicSquareSum) {
                isValid = false;
                System.out.print("Column #" + (i+1) + " does not produce the expected magic square sum ");
                System.out.println("for a " + magicSquare.size() + " magic square (" + magicSquareSum + ")");
            }
        }
        return isValid;
    }

    /*
     * Method Name: checkRowsAndColumns
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Determines whether or not the sums of the diagonals of the magic square
     * currently under consideration by this MagicSquareValidator are equal to the
     * relevant magic square sum (and thus satisfy the requirements of a magic square).
     *
     * Method Design Description
     * Determines the sum of both diagonals in parallel and reports any diagonals with
     * component values that do not sum to the relevant magic square sum to the user via
     * the console.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * Console outputs (for displaying information concerning all instances where the sum
     * of a diagonal of the magic square currently under consideration by this
     * MagicSquareValidator is not equal to the appropriate magic square sum, if any).
     *
     * Returns:
     * True if there are no issues with the sums of the diagonals of the magic square
     * currently loaded into this MagicSquareValidator, false otherwise.
     */
    private boolean checkDiagonals() {
        int forwardSum = 0;
        int backwardSum = 0;
        boolean isValid = true;
        for (int i = 0; i < magicSquare.size(); i++) {
            forwardSum += magicSquare.get(i).get(i); // i is the row and column number
            int backwardIndex = magicSquare.size()-i-1; // inverting the row and column number
            backwardSum += magicSquare.get(backwardIndex).get(backwardIndex);
        }
        if (forwardSum != magicSquareSum) {
            isValid = false;
            System.out.print("The forward diagonal does not produce the expected magic square sum ");
            System.out.println("for a " + magicSquare.size() + " magic square (" + magicSquareSum + ")");
        }
        if (backwardSum != magicSquareSum) {
            isValid = false;
            System.out.print("The backward diagonal does not produce the expected magic square sum ");
            System.out.println("for a " + magicSquare.size() + " magic square (" + magicSquareSum + ")");
        }
        return isValid;
    }

    /*
     * Method Name: isMagicSquare
     * Date: 2019-10-29
     * Author: Daniel DeAnda
     *
     * Method Purpose
     * Determines whether or not the magic square currently under consideration by this
     * MagicSquareValidator is in fact a valid magic square.
     *
     * Method Design Description
     * First determines if the elements of the magic square in question are valid, then
     * checks the validity of the the rows/columns and diagonals (in that order) based on
     * their sums.
     * NOTE: The first faulty check is also the last check to report issues to the console;
     * in other words, the first category of checks that fails will result in subsequent
     * categories of checks not being performed, so a faulty magic square may have more
     * problems than are displayed to the end user, which are simply the first ones
     * encountered by this algorithm.
     *
     * Inputs:
     * None
     *
     * Outputs:
     * None, except for those of called methods.
     *
     * Returns:
     * True if the magic square currently loaded into this MagicSquareValidator is in fact
     * a magic square, false otherwise.
     */
    public boolean isMagicSquare() {
        if (this.checkElements()) {
            if (this.checkRowsAndColumns()) {
                return this.checkDiagonals();
            }
        }
        return false;
    }

}
