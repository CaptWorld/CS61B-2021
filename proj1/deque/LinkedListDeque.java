package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        public T _item;
        public Node prev;
        public Node next;

        public Node(T item) {
            _item = item;
        }
    }

    private final Node sentinel = new Node(null);

    /**
     * Invariants:
     * 1. The size variable always holds size of the list
     * 2. The sentinel variable always points to sentinel
     * 3. The first node (if exists), is always at sentinel.next
     * 4. The last node (if exists), is always at sentinel.prev
     */

    private Node items;
    private int size;

    public LinkedListDeque() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        items = sentinel;
        size = 0;
    }

    /**
     * Adds item to the start of the deque.
     *
     * @param item the object to be added to the start of the deque
     */
    @Override
    public void addFirst(T item) {
        Node itemNode = new Node(item);
        sentinel.next.prev = itemNode;
        itemNode.next = sentinel.next;
        itemNode.prev = sentinel;
        sentinel.next = itemNode;
        size += 1;
    }

    /**
     * Adds item to the end of the deque.
     *
     * @param item the object to be added to the end of the deque
     */
    @Override
    public void addLast(T item) {
        Node itemNode = new Node(item);
        sentinel.prev.next = itemNode;
        itemNode.next = sentinel;
        itemNode.prev = sentinel.prev;
        sentinel.prev = itemNode;
        size += 1;
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
        int i = 0;
        for (T item : this) {
            if (i != 0) {
                System.out.print(' ');
            }
            i++;
            System.out.print(item);
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
        Node firstItem = sentinel.next;
        if (firstItem == sentinel) {
            return null;
        }

        sentinel.next = firstItem.next;
        firstItem.next.prev = sentinel;
        size -= 1;

        return firstItem._item;
    }

    /**
     * Removes the last item from the deque.
     *
     * @return the item at end of deque, if deque is not empty, else null
     */
    @Override
    public T removeLast() {
        Node lastItem = sentinel.prev;
        if (lastItem == sentinel) {
            return null;
        }

        lastItem.prev.next = sentinel;
        sentinel.prev = lastItem.prev;
        size -= 1;

        return lastItem._item;
    }

    /**
     * Provides item stored at index position, using iteration.
     *
     * @param index the position in deque to get the item
     * @return the item stored at index position, if valid index is provided, else null
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node itemNode = sentinel.next;
        int i = 0;
        while (i != index) {
            itemNode = itemNode.next;
            i++;
        }

        return itemNode._item;
    }

    /**
     * Provides item stored at index position, using recursion.
     *
     * @param index the position in deque to get the item
     * @return the item stored at index position, if valid index is provided, else null
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, 0, index);
    }

    private T getRecursive(Node item, int currentIndex, int targetIndex) {
        if (currentIndex == targetIndex) {
            return item._item;
        } else {
            return this.getRecursive(item.next, currentIndex + 1, targetIndex);
        }
    }

    /**
     * Provides iterator to loop through the deque.
     *
     * @return iterator to iterate through the deque
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node itemNode = sentinel.next;

            @Override
            public boolean hasNext() {
                return itemNode != sentinel;
            }

            @Override
            public T next() {
                T item = itemNode._item;
                itemNode = itemNode.next;
                return item;
            }
        };
    }
}
