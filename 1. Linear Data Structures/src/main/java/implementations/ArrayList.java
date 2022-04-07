package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private static final int INITIAL_SIZE = 4;
    private Object[] elements;
    private int size;
    private int capacity;

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        this.size = 0;
        this.capacity = INITIAL_SIZE;
    }

    @Override
    public boolean add(E element) {
        if (ifGivenSizeIsEqualToSizeOfElements()) {
            grow();
        }

        this.elements[this.size++] = element;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        checkIndex(index);

        if (ifGivenSizeIsEqualToSizeOfElements()) {
            grow();
        }

        shiftElementsToRight(index);
        this.elements[index] = element;
        this.size++;

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);

        return (E) this.elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);

        Object existing = this.elements[index];

        this.elements[index] = element;

        return (E) existing;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);

        Object existing = this.elements[index];

        shiftElementsToLeft(index);
        ensureCapacity();
        this.size--;

        return (E) existing;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {

            if (this.elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E element) {
        return this.indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
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

    private boolean ifGivenSizeIsEqualToSizeOfElements() {

        return this.size == capacity;
    }

    private void grow() {
        capacity = capacity * 2;

        Object[] tmp = new Object[this.capacity];
        System.arraycopy(this.elements, 0, tmp, 0, this.elements.length);

        this.elements = tmp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {

            throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d for size %d",
                    index, this.size));
        }
    }

    private void shiftElementsToRight(int index) {
        for (int i = this.size - 1; i >= index; i--) {

            this.elements[i + 1] = this.elements[i];
        }
    }

    private void shiftElementsToLeft(int index) {

        for (int i = index; i < this.size - 1; i++) {

            this.elements[i] = this.elements[i + 1];
        }
    }

    private void ensureCapacity() {
        if (this.size < this.elements.length / 3) {
            this.elements = shrink();
        }
    }

    private Object[] shrink() {
        return Arrays.copyOf(this.elements, this.elements.length / 2);
    }
}
