package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> defaultComparator;
    private T max;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        defaultComparator = c;
    }

    public T max() {
        return max(defaultComparator);
    }

    private T max(Comparator<T> c) {
        T max = null;
        for (T item : this) {
            if (c.compare(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }

}
