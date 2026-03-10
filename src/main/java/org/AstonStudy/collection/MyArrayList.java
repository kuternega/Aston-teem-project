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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(elements, size, a.getClass());
        }

        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        Objects.requireNonNull(collection, "Коллекция не может быть null");

        if (collection.isEmpty()) {
            return false;
        }

        int newSize = size + collection.size();
        ensureCapacity(newSize);

        Object[] newElements = Arrays.copyOf(elements, newSize);
        int i = size;

        for (T element : collection) {
            newElements[i++] = element;
        }

        elements = newElements;
        size = newSize;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Objects.requireNonNull(c, "Коллекция не может быть null");
        if (c.isEmpty()) {
            return false;
        }
        int cSize = c.size();
        ensureCapacity(size + cSize);
        System.arraycopy(elements, index, elements, index + cSize, size - index);
        int i = index;
        for (T element : c) {
            elements[i++] = element;
        }
        size += cSize;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c, "Коллекция не может быть null");
        boolean modified = false;
        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(elements[i])) {
                remove(i);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c, "Коллекция не может быть null");
        boolean modified = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < size(); i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removed = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elements[i])) return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return new MyListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException(
                    "fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", size: " + size);
        }
        return new SubList(this, fromIndex, toIndex);
    }

    private class MyIterator implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) elements[cursor++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() не поддерживается в этом итераторе");
        }
    }

    private class MyListIterator implements ListIterator<T> {
        private int cursor;
        private int lastRet = -1;

        MyListIterator(int index) {
            cursor = index;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastRet = cursor;
            return (T) elements[cursor++];
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastRet = --cursor;
            return (T) elements[cursor];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            MyArrayList.this.remove(lastRet);
            if (lastRet < cursor) {
                cursor--;
            }
            lastRet = -1;
        }

        @Override
        public void set(T t) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            MyArrayList.this.set(lastRet, t);
        }

        @Override
        public void add(T t) {
            MyArrayList.this.add(cursor, t);
            cursor++;
            lastRet = -1;
        }
    }

    private class SubList extends AbstractList<T> {
        private final MyArrayList<T> outer;
        private final int offset;
        private int subSize;

        SubList(MyArrayList<T> outer, int fromIndex, int toIndex) {
            this.outer = outer;
            this.offset = fromIndex;
            this.subSize = toIndex - fromIndex;
        }

        private void checkIndex(int index) {
            if (index < 0 || index >= subSize) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + subSize);
            }
        }

        @Override
        public T get(int index) {
            checkIndex(index);
            return outer.get(offset + index);
        }

        @Override
        public T set(int index, T element) {
            checkIndex(index);
            return outer.set(offset + index, element);
        }

        @Override
        public void add(int index, T element) {
            if (index < 0 || index > subSize) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + subSize);
            }
            outer.add(offset + index, element);
            subSize++;
            modCount++; // Для поддержки итераторов AbstractList
        }

        @Override
        public T remove(int index) {
            checkIndex(index);
            T removed = outer.remove(offset + index);
            subSize--;
            modCount++;
            return removed;
        }

        @Override
        public int size() {
            return subSize;
        }
    }
}