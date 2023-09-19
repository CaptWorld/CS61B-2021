package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private final Comparator<T> defaultComparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        defaultComparator = c;
    }

    public T max() {
        return max(defaultComparator);
    }

    public T max(Comparator<T> c) {
        T max = null;
        for (int i = 0; i < size(); i++) {
            T item = get(i);
            if (max == null || c.compare(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }

}
