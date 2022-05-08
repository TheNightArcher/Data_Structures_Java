package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private List<E> elements;

    public PriorityQueue() {
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

    private boolean isLess(int firstIndex, int secondIndex) {
        return getAt(firstIndex).compareTo(getAt(secondIndex)) < 0;
    }

    private E getAt(int i) {
        return this.elements.get(i);
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return getAt(0);
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E returnedValue = getAt(0);
        Collections.swap(this.elements, 0, this.size() - 1);

        this.elements.remove(this.size() - 1);
        this.heapifyDown(0);
        return returnedValue;
    }

    private E getLeftChild(int index) {
        return this.elements.get(this.getLeftChildIndex(index));
    }

    private E getRightChild(int index) {
        return this.elements.get(this.getRightChildIndex(index));
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }


    private void heapifyDown(int index) {
        while (getLeftChildIndex(index) < this.size() && isLess(index, getLeftChildIndex(index))) {
            int child = getLeftChildIndex(index);
            int rightChildIndex = getRightChildIndex(index);

            if (rightChildIndex < this.size() && isLess(child, rightChildIndex)) {
                child = rightChildIndex;
            }

            Collections.swap(this.elements, child, index);
            index = child;
        }
    }

    private void ensureNonEmpty() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}

