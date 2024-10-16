package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length <= size) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T old = get(index);
        container[index] = newValue;
        return old;
    }

    @Override
    public T remove(int index) {
        T old = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - 1 - index);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return old;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int point = 0;
            int exprctionModCount = modCount;

            @Override
            public boolean hasNext() {
                if (exprctionModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }

    private void grow() {
        container = container.length == 0 ? (T[]) new Object[2]
                : Arrays.copyOf(container, container.length * 2);
    }
}