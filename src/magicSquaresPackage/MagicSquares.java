package magicSquaresPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

@SuppressWarnings("WeakerAccess")
public class MagicSquares {

    private Scanner file = null;

    private Vector<Integer> numElements;
    private Vector<Vector<Integer>> magicSquare;

    public MagicSquares() {
        numElements = new Vector<>();
        magicSquare = new Vector<>();
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
            numElements.clear();
            numElements.setSize(size*size); // elements range from 1-25; ignoring index 0

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

    private boolean checkElements() {
        return false;
    }

    private boolean checkRowsAndColumns() {
        return false;
    }

    private boolean checkDiagonals() {
        return false;
    }

    public boolean isMagicSquare() {
        if (!this.checkElements()) {
            return false;
        }
        if (!this.checkRowsAndColumns()) {
            return false;
        }
        if (!this.checkDiagonals()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        MagicSquares validator = new MagicSquares();

        boolean successful = validator.openFile("magic.txt");
        if (successful) {

            do {

                boolean atEndOfFile = validator.loadNextSquare();
                if (atEndOfFile) {
                    break;
                }

                if (validator.isMagicSquare()) {
                    // print a boy
                } else {
                    // say it isn't
                }


            } while (true);

        }

    }

}
