package ru.otus.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * DIYArrayList.
 *
 * @author Evgeniy_Medvedev
 */
public class DIYArrayList<E> implements List<E> {

    private int size = 0;
    private Object[] elements;
    private int capacity = 10;
    private Iterator<E> iterator;

    public DIYArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    capacity);
        }
        size = capacity;
        this.elements = new Object[capacity];
    }

    public DIYArrayList() {
        this.elements = new Object[capacity];
    }

    public DIYArrayList(List<E> list) {
        this.elements = list.toArray();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 || elements == null;
    }

    @Override
    public boolean contains(Object o) {
        for (Object exp : elements) {
            return exp.equals(0);
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        if (iterator == null) {
            iterator = new AIYIterator();
        }
        return iterator;
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return (E[]) Arrays.copyOf(elements, size, a.getClass());
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length - 1) {
            Object[] newArray = new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        elements[size] = e;
        size++;

        return elements[size] != null;
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemove = false;
        for (int i = 0; i < size - 1; i++) {
            if (elements[i].equals(o)) {
                elements[i] = null;
                size--;
                isRemove = true;
            }
        }
        return isRemove;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            return true;
        }

        boolean isContains = true;
        Object[] objects = c.toArray();
        for (int i = 0; i < size; i++) {
            if (!contains(objects[i])) {
                isContains = false;
            }
        }
        return isContains;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] objects = c.toArray();
        if (elements.length - size < c.size()) {
            Object[] newArray = new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        System.arraycopy(objects, 0, elements, size, c.toArray().length);
        size += objects.length;


        return objects.length != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Object[] objects = c.toArray();
        if (elements.length - size < c.size()) {
            Object[] newArray = new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        System.arraycopy(objects, 0, elements, index, c.toArray().length);
        size = elements.length;

        return c.size() != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int cursor = 0;
        if (c == null || c.isEmpty()) {
            return false;
        }
        boolean isRemove = false;
        Object[] objects = c.toArray();
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(objects[cursor])) {
                elements[i] = null;
                cursor++;
                size--;
                isRemove = true;
            }
        }
        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        elements = new Object[0];
    }

    @Override
    public E get(int index) {
        if (index > elements.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (index > elements.length || index < 0) {
            throw new IndexOutOfBoundsException(
                    String.format("Индекс превышает размер коллекции или меньше нуля, для элемента %s метод set() провалился", element));
        }
        elements[index] = element;
        size++;
        return (E) elements[index];
    }

    @Override
    public void add(int index, E element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == elements.length - 1) {
            Object[] newArray = new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
            size++;
        }
        Logger.getGlobal().info("length - " + elements.length + Arrays.toString(elements));
        elements[index] = element;
    }

    @Override
    public E remove(int index) {
        if (index > elements.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == elements.length - 1) {
            Object[] newArray = new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        size--;
        elements[index] = null;
        return (E) elements[index];
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DIYListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private class AIYIterator implements Iterator<E> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index + 1< elements.length;
        }

        @Override
        public E next() {
            if (hasNext())
                return (E) elements[index++];

            return null;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    private class DIYListIterator extends AIYIterator implements ListIterator<E> {

        int cursor = 0;

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer action) {

        }

        @Override
        public void set(E e) {
            if (hasNext()) {
                DIYArrayList.this.set(cursor++, e);
            }
        }

        @Override
        public void add(E e) {
            if (hasNext()) {
                DIYArrayList.this.set(cursor++, e);
            }
        }
    }
}