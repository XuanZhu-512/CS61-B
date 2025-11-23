package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;
    private int size;

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private BSTNode getNode(BSTNode node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) return getNode(node.right, key);
        else if (cmp < 0) return getNode(node.left, key);
        else return node;
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        BSTNode node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public int size() {
        return size;
    }

    private BSTNode insert(BSTNode node, K key, V value) {
        if (node == null) {
            size++;
            return new BSTNode(key, value);
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) node.left = insert(node.left, key, value);
        else if (cmp > 0) node.right = insert(node.right, key, value);
        else node.value = value;

        return node;
    }

    @Override
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    private void inorderTraversal(Set<K> s, BSTNode node) {
        if (node == null) return;
        inorderTraversal(s, node.left);
        s.add(node.key);
        inorderTraversal(s, node.right);
    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<K>();
        inorderTraversal(s, root);
        return s;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private void printInOrder(BSTNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.println(node.key);
        printInOrder(node.right);
    }

    public void printInOrder() {
    printInOrder(root);
    }
}
