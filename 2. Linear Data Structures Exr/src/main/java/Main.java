import implementations.ArrayDeque;

public class Main {
    public static void main(String[] args) {

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.get(1));

    }
}
