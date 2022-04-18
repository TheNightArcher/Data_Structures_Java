package implementations;

import interfaces.ReversedList;

import java.util.Iterator;

public class ReversedListImpl<E> implements ReversedList<E> {
    private static int INITIAL_CAPACITY = 2;

    private Object[] elements;
    private int size;

    public ReversedListImpl() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(E element) {
        if (this.size == this.elements.length - 1) {
            this.elements = grow();
        }

        this.elements[size++] = element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);

        Object[] reversedElements = reversed();
        return (E) reversedElements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E removeAt(int index) {
        checkIndex(index);
        Object[] reversedElements = reversed();

        E element = (E) reversedElements[index];
        reversedElements[index] = null;
        this.size--;
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private Object[] grow() {
        int newCapacity = this.elements.length * 2;
        Object[] newElements = new Object[newCapacity];

        for (int i = 0; i < this.elements.length; i++) {
            newElements[i] = this.elements[i];
        }
        return newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > this.size) {

            throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d for size %d",
                    index, this.size));
        }
    }

    private Object[] reversed() {
        Object[] reversedElements = new Object[elements.length];

        int reversedIndex = 0;
        for (int i = this.size - 1; i > 0; i--) {

            reversedElements[reversedIndex] = this.elements[i];
            reversedIndex++;
        }
        return reversedElements;
    }
}
