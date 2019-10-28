package magicSquaresPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

@SuppressWarnings("WeakerAccess")
public class MagicSquares {

    private Scanner file = null;
    private String separator = "--------------------------------------------";

    private Vector<Integer> numElements;
    private Vector<Vector<Integer>> magicSquare;
    private int magicSquareSum;

    public MagicSquares() {
        numElements = new Vector<>();
        magicSquare = new Vector<>();
        magicSquareSum = 0;
    }

    public int getCurrentSquareSize() {
        return magicSquare.size();
    }

    public int getCurrentMagicSum() {
        return magicSquareSum;
    }

    public boolean openFile(String path) {
        try {
            FileReader reader = new FileReader(path);
            file = new Scanner(reader);
        } catch (FileNotFoundException e) { // IOException?
            System.out.println("ERROR: File " + path + " did not open successfully.");
            return false;
        }
        return true;
    }

    public boolean loadNextSquare() {
        if (file.hasNextLine()) {

            int size = Integer.parseInt(file.nextLine());
            magicSquare.clear();
            magicSquare.setSize(size);
            magicSquareSum = (size*(size*size+1))/2;

            for (int row = 0; row < size; row++) {

                magicSquare.set(row, new Vector<>(size));
                String[] currentRow = file.nextLine().split(" ");

                for (int column = 0; column < size; column++) {
                    int currentValue = Integer.parseInt(currentRow[column]);
                    magicSquare.get(row).set(column, currentValue);
                }

            }

            return true;

        }
        return false;
    }

    public void printCurrentSquare() {

    }

    private boolean checkElements() {

        int size = magicSquare.size();
        numElements.clear();
        numElements.setSize(size*size); // elements range from 1-25; ignoring index 0
        for (int i = 1; i < numElements.size(); i++) {
            numElements.set(i, 0);
        }

        boolean isValid = true;

        for (int row = 0; row < magicSquare.size(); row++) {
            for (int column = 0; column < magicSquare.size(); column++) {

                int now = magicSquare.get(row).get(column);
                System.out.println(numElements.size());
                if (now >= 1 && now <= numElements.size()) {
                    numElements.set(now, numElements.get(now)+1);
                } else {
                    isValid = false;
                    System.out.print("The square has an entry " + now + "that is greater than ");
                    System.out.println(size + " squared which is " + numElements.size());
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
                System.out.println("Row #" + i + " does not produce the expected magic square sum ");
                System.out.println("for a " + magicSquare.size() + "magic square (" + magicSquareSum + ")");
            } else if (columnSum != magicSquareSum) {
                isValid = false;
                System.out.println("Column #" + i + " does not produce the expected magic square sum ");
                System.out.println("for a " + magicSquare.size() + "magic square (" + magicSquareSum + ")");
            }
        }
        return isValid;
    }

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
            System.out.println("The forward diagonal does not produce the expected magic square sum ");
            System.out.println("for a " + magicSquare.size() + "magic square (" + magicSquareSum + ")");
        } else if (backwardSum != magicSquareSum) {
            isValid = false;
            System.out.println("The backward diagonal does not produce the expected magic square sum ");
            System.out.println("for a " + magicSquare.size() + "magic square (" + magicSquareSum + ")");
        }
        return isValid;
    }

    public boolean isMagicSquare() {
        if (this.checkElements()) {
            if (this.checkRowsAndColumns()) {
                return this.checkDiagonals();
            }
        }
        return false;
    }

    public static void main(String[] args) {

        MagicSquares validator = new MagicSquares();

        boolean successful = validator.openFile("magic.txt");
        if (successful) {

            do {

                // Indicate a new "screen"
                System.out.println(validator.separator);

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

            } while (true);

        }

    }

}
