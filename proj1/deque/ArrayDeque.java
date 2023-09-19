package deque;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private static final double LOAD_FACTOR = 0.25;

    private static final int MIN_SIZE_FOR_RESIZING = 16;

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
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        items[nextFirst] = item;
        nextFirst--;
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
        if (nextLast == items.length) {
            nextLast = 0;
        }
        items[nextLast] = item;
        nextLast += 1;
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
        if (items.length >= MIN_SIZE_FOR_RESIZING &&
                size == Math.round(LOAD_FACTOR * items.length)) {
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
        if (items.length >= MIN_SIZE_FOR_RESIZING && size == Math.round(LOAD_FACTOR * items.length)) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ArrayDeque<T> castedObj = (ArrayDeque<T>) obj;
        if (size() != castedObj.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(castedObj.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> adq = new ArrayDeque<>();
        LinkedListDeque<Integer> ldq = new LinkedListDeque<>();
        int iterations = 50000;
        for (int i = 0; i < iterations; i++) {
            double prob = Math.random();
            if (prob < 0.25) {
                int random = (int) Math.round(Math.random() * iterations);
                ldq.addFirst(random);
                adq.addFirst(random);
                System.out.println("AddFirst: " + random);
                assertEquals(ldq.get(0), adq.get(0));
            } else if (prob < 0.5) {
                int random = (int) Math.round(Math.random() * iterations);
                ldq.addLast(random);
                adq.addLast(random);
                System.out.println("AddLast: " + random);
                assertEquals(ldq.get(ldq.size() - 1), adq.get(adq.size() - 1));
            } else if (prob < 0.75) {
                Integer expected = ldq.removeFirst();
                Integer actual = adq.removeFirst();
                System.out.println("removeFirst: " + expected + " " + actual);
                assertEquals(expected, actual);
            } else {
                Integer expected = ldq.removeLast();
                Integer actual = adq.removeLast();
                System.out.println("removeLast: " + expected + " " + actual);
                assertEquals(expected, actual);
            }
        }
    }
}
