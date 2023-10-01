package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private BSTNode root;
    private int size;

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        return switch (node.key.compareTo(key)) {
            case 0 -> node.value;
            case 1 -> get(node.left, key);
            default -> get(node.right, key);
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        size++;
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if (node == null) {
            return new BSTNode(key, value, null, null);
        }
        switch (node.key.compareTo(key)) {
            case 0:
                node.value = value;
                break;
            case 1:
                node.left = put(node.left, key, value);
                break;
            default:
                node.right = put(node.right, key, value);
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
//        return remove(root, key);
        throw new UnsupportedOperationException();
    }

//    private V remove(BSTNode node, K key) {
//        if (node == null) {
//            return null;
//        }
//        switch (node.key.compareTo(key)) {
//            case 0:
//                if (node.left
//                break;
//            case 1:
//                return remove(node.left, key);
//            default:
//                return remove(node.right, key);
//        }
//    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        throw new UnsupportedOperationException();
    }
}
