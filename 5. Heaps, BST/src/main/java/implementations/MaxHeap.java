package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private List<E> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int i) {
        while (i >= 0 && isLess(getParentIndex(i), i)) {
            Collections.swap(this.elements, getParentIndex(i), i);
            i = getParentIndex(i);
        }
    }

    private boolean isLess(int parentIndex, int childIndex) {
        return getAt(parentIndex).compareTo(getAt(childIndex)) < 0;
    }

    private E getAt(int i) {
        return this.elements.get(i);
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    @Override
    public E peek() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException();
        }
        return getAt(0);
    }
}
