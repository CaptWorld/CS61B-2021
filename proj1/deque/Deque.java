package deque;

import java.util.Iterator;

/**
 * Interface for Deque Data Structure
 *
 * @see <a href="http://www.cplusplus.com/reference/deque/deque/">http://www.cplusplus.com/reference/deque/deque/</a>
 *
 * @param <T> type of items stored in the structure
 */
 public interface Deque<T> {

    /**
     * Adds item to the start of the deque.
     *
     * @param item the object to be added to the start of the deque
     */
     void addFirst(T item);

    /**
     * Adds item to the end of the deque.
     *
     * @param item the object to be added to the end of the deque
     */
     void addLast(T item);

    /**
     * Checks if the deque is empty.
     *
     * @return true if deque is empty, false otherwise
     */
     default boolean isEmpty() {
         return size()  == 0;
     }

    /**
     * Provides size of the deque.
     *
     * @return the size of the deque
     */
     int size();

    /**
     * Prints the items in deque with space in between and newline at the end.
     */
     void printDeque();

    /**
     * Removes the first item from the deque.
     *
     * @return the item at start of deque, if deque is not empty, else null
     */
     T removeFirst();

    /**
     * Removes the last item from the deque.
     *
     * @return the item at end of deque, if deque is not empty, else null
     */
     T removeLast();

    /**
     * Provides item stored at index position.
     *
     * @param index the position in deque to get the item
     *
     * @return the item stored at index position, if valid index is provided, else null
     */
     T get(int index);

    /**
     * Provides iterator to loop through the deque.
     *
     * @return iterator to iterate through the deque
     */
     Iterator<T> iterator();

    /**
     * Checks if current deque and object o are equal.
     * They are equal if o is instance of Deque and contains same elements in order.
     *
     * @param o the object to be compared with
     * @return true, if current deque and o are equal, else false
     */
     boolean equals(Object o);
}
