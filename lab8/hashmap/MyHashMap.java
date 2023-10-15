package hashmap;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final float RESIZE_FACTOR = 1.75F;

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {


        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }

    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private final double maxLoad;
    private int size = 0;

    /**
     * Constructors
     */
    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.maxLoad = maxLoad;
        this.buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        buckets = createTable(DEFAULT_INITIAL_SIZE);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        for (Node node : buckets[getIndex(buckets, key)]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }


    private int getIndex(Collection<Node>[] buckets, K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        if ((double) size / buckets.length > maxLoad) {
            resize(Math.round(RESIZE_FACTOR * buckets.length));
        }
        if (put(buckets, key, value)) {
            size += 1;
        }
    }

    private boolean put(Collection<Node>[] buckets, K key, V value) {
        int index = getIndex(buckets, key);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return false;
            }
        }
        buckets[index].add(createNode(key, value));
        return true;
    }

    private void resize(int newBucketSize) {
        Collection<Node>[] newBuckets = createTable(newBucketSize);
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                put(newBuckets, node.key, node.value);
            }
        }
        buckets = newBuckets;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (K key : this) {
            keys.add(key);
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                if (node.key.equals(key)) {
                    bucket.remove(node);
                    size--;
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            return null;
        }
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                if (node.key.equals(key) && node.value.equals(value)) {
                    bucket.remove(node);
                    size--;
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int currentBucketIndex = 0;
            Iterator<Node> currentBucketIterator;

            @Override
            public boolean hasNext() {
                while (currentBucketIndex < buckets.length) {
                    if (currentBucketIterator == null) {
                        currentBucketIterator = buckets[currentBucketIndex].iterator();
                    }
                    if (currentBucketIterator.hasNext()) {
                        return true;
                    } else {
                        currentBucketIndex++;
                        currentBucketIterator = null;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                return currentBucketIterator.next().key;
            }
        };
    }
}
