package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {
    private List<E> elements;

    public MinHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapIfDown(this.size() - 1);
    }

    @Override
    public E peek() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException();
        }

        return getAt(0);
    }

    @Override
    public E poll() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException();
        }

        E currentElement = this.elements.get(0);
        this.elements.remove(0);

        return currentElement;
    }

    @Override
    public void decrease(E element) {
        int indexOfElement = this.elements.indexOf(element);

        E current = this.elements.get(indexOfElement);

        current.decrease();

        heapIfDown(indexOfElement);
    }

    private E getAt(int index) {
        return this.elements.get(index);
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private void heapIfDown(int i) {
        while (i >= 0 && isGreater(getParentIndex(i), i)) {
            Collections.swap(this.elements, getParentIndex(i), i);
            i = getParentIndex(i);
        }
    }

    private boolean isGreater(int parentIndex, int childIndex) {
        return getAt(parentIndex).compareTo(getAt(childIndex)) > 0;
    }
}
