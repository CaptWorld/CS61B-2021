package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArrayDeque<T> implements Deque<T> {

    private static final double LOAD_FACTOR = 0.25;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private static int getInitialNextFirst(int sizeOfArr) {
        return sizeOfArr / 2;
    }

    private static int getInitialNextLast(int sizeOfArr) {

        return sizeOfArr / 2 + 1;
    }

    /**
     * resizes items array to new size with all elements intact
     *
     * @param newSize of the items array
     */
    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        for (int i = 0; i < size(); i++) {
            newItems[i] = get(i);
        }
        items = newItems;
        nextFirst = newSize - 1;
        nextLast = size;
    }

    /**
     * Adds item to the start of the deque.
     *
     * @param item the object to be added to the start of the deque
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst--;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        size++;
    }

    /**
     * Adds item to the end of the deque.
     *
     * @param item the object to be added to the end of the deque
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast += 1;
        if (nextLast == items.length) {
            nextLast = 0;
        }
        size++;
    }

    /**
     * Provides size of the deque.
     *
     * @return the size of the deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in deque with space in between and newline at the end.
     */
    @Override
    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(get(i));
        }
        System.out.println();
    }

    /**
     * Removes the first item from the deque.
     *
     * @return the item at start of deque, if deque is not empty, else null
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size == Math.round(LOAD_FACTOR * items.length)) {
            resize((int) Math.round(LOAD_FACTOR * items.length));
        }
        nextFirst = nextFirst == items.length - 1 ? 0 : nextFirst + 1;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return item;
    }

    /**
     * Removes the last item from the deque.
     *
     * @return the item at end of deque, if deque is not empty, else null
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size == Math.round(LOAD_FACTOR * items.length)) {
            resize((int) Math.round(LOAD_FACTOR * items.length));
        }
        nextLast = nextLast == 0 ? items.length - 1 : nextLast - 1;
        T lastItem = items[nextLast];
        items[nextLast] = null;
        size--;
        return lastItem;
    }

    /**
     * Provides item stored at index position.
     *
     * @param index the position in deque to get the item
     * @return the item stored at index position, if valid index is provided, else null
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    /**
     * Provides iterator to loop through the deque.
     *
     * @return iterator to iterate through the deque
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                T item = get(i);
                i++;
                return item;
            }
        };
    }
}
