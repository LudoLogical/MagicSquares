import java.util.Vector;

public class Playground {

    public static void main(String[] args) {
        int size = 5;
        Vector<Vector<Integer>> fooVector = new Vector<>(size);
        for (int i = 0; i < size; i++) {
            fooVector.add(new Vector<>(size));
        }
        System.out.println(fooVector);
        fooVector.clear();
        System.out.println(fooVector.capacity());

        int intValue = 4;
        System.out.printf("%3d", intValue);
        // sys.in.read?

        Vector<Integer> count = new Vector<>();
        count.setSize(size*size);
        System.out.println(count);
        count.set(22,5);
        System.out.println(count);

    }

}
