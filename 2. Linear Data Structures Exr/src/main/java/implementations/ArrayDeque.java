package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private final int DEFAULT_CAPACITY = 7;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        int middle = DEFAULT_CAPACITY / 2;
        head = tail = middle;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void offer(E element) {
        addLast(element);
    }

    @Override
    public void addFirst(E element) {

    }

    @Override
    public void addLast(E element) {
        if (SizeEqualsToCapacity()) {
            grow();
        }


        this.elements[this.size++] = element;
    }

    @Override
    public void push(E element) {

    }

    @Override
    public void insert(int index, E element) {

    }

    @Override
    public void set(int index, E element) {

    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E get(Object object) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E remove(Object object) {
        return null;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public void trimToSize() {

    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private boolean SizeEqualsToCapacity() {
        return this.size == this.capacity;
    }

    private void grow() {
        this.capacity = capacity * 2;

        Object[] tmp = new Object[capacity];

        System.arraycopy(this.elements, 0, tmp, 0, this.elements.length);

        this.elements = tmp;
    }
}
