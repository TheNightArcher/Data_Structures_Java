import implementations.ReversedListImpl;
import interfaces.ReversedList;

public class Main {
    public static void main(String[] args) {

        ReversedList<Integer> queue = new ReversedListImpl<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(7);


        System.out.println(queue.removeAt(0));
        System.out.println(queue.get(0));
    }
}
