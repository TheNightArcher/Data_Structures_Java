import implementations.ArrayDeque;

public class Main {
    public static void main(String[] args) {

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        queue.push(1);
        queue.push(2);

        System.out.println(queue.peek());


    }
}
