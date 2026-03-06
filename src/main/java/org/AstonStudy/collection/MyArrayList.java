package org.AstonStudy.collection;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;


    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }


    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Емкость не может быть отрицательной: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }


    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне границ: " + index + ", размер: " + size);
        }
    }


    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity > oldCapacity) {

            int newCapacity = oldCapacity + (oldCapacity >> 1);

            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) elements[index];
        elements[index] = element;
        return oldValue;
    }

    @Override
    public boolean add(T element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public boolean contains(Object o) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод contains будет реализован позже");
    }

    @Override
    public Iterator<T> iterator() {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод iterator будет реализован позже");
    }

    @Override
    public Object[] toArray() {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод toArray будет реализован позже");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод toArray(T1[]) будет реализован позже");
    }

    @Override
    public boolean remove(Object o) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод remove будет реализован позже");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод containsAll будет реализован позже");
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        Objects.requireNonNull(collection, "Коллекция не может быть null");

        if (collection.isEmpty()) {
            return false;
        }

        int newSize = size + collection.size();
        Object[] newElements = Arrays.copyOf(elements, newSize);
        int i = size;

        for (T element : collection) {
            elements[i++] = element;
        }

        elements = newElements;
        size = newSize;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод addAll(int, Collection) будет реализован позже");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод removeAll будет реализован позже");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод retainAll будет реализован позже");
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }
        if (size() == MyArrayList.DEFAULT_CAPACITY) {
            for (int i = 0; i < DEFAULT_CAPACITY; i++) {
                // Очистка коллекции, стоит заглушка пока нет remove()
                elements[i] = null;
            }
            size = 0;
        } else {
            for (int i = 0; i < size(); i++) {
                // Очистка коллекции, стоит заглушка пока нет remove()
                elements[i] = null;
            }
            size = 0;
        }
    }

    @Override
    public void add(int index, T element) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод add(int, T) будет реализован позже");
    }

    @Override
    public T remove(int index) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод remove(int) будет реализован позже");
    }

    @Override
    public int indexOf(Object o) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод indexOf будет реализован позже");
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод lastIndexOf будет реализован позже");
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод listIterator будет реализован позже");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод listIterator(int) будет реализован позже");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // TODO: будет реализовано позже
        throw new UnsupportedOperationException("Метод subList будет реализован позже");
    }
}
