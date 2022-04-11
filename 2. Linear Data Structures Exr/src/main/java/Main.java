import implementations.DoublyLinkedList;

public class Main {
    public static void main(String[] args) {

        DoublyLinkedList<Integer> queue = new DoublyLinkedList<>();

        queue.addFirst(1);


        for (int e : queue) {

            System.out.println(e);
        }
    }
}
