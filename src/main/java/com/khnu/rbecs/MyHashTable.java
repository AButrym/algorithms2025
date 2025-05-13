package com.khnu.rbecs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashTable<K, V> implements MyDictionary<K, V> {
    public static final float LOAD_FACTOR = 0.75f;
//    public static final float RESIZE_FACTOR = 2.0f;
    private static final int DEFAULT_CAPACITY = 8;
    private int mask = 0b111;
    private Node[] bins = new Node[DEFAULT_CAPACITY];
    private int size = 0;

    private int ixBin(K key) {
        return key.hashCode() & mask;
//        return Math.abs(key.hashCode()) % bins.length;
    }

    @Override
    public V put(K key, V value) {
        Node<K, V> node = find(key);
        if (node != null) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        Node<K, V> newNode = new Node<>(key, value);
        int ixBin = ixBin(key);
        newNode.next = bins[ixBin];
        bins[ixBin] = newNode;
        size++;
        if (size > bins.length * LOAD_FACTOR) {
            resize();
        }
        return null;
    }

    private void resize() {
        int newCapacity = bins.length * 2;
        mask = (mask << 1) | 1;
        Node<K,V>[] newBins = new Node[newCapacity];
        for (Entry<K,V> entry : this) {
            Node<K,V> node = (Node<K, V>) entry;
            int ixBin = ixBin(node.key);
            node.next = newBins[ixBin];
            newBins[ixBin] = node;
        }
        bins = newBins;
    }

    private Node<K,V> find(K key) {
        int ixBin = ixBin(key);
        Node<K, V> node = bins[ixBin];
        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = find(key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        Node<K, V> node = find(key);
        if (node == null) return null;
        int ixBin = ixBin(key);
        Node<K,V> cur = bins[ixBin];
        if (cur == node) {
            bins[ixBin] = node.next;
        } else {
            while (cur.next != node) {
                cur = cur.next;
            }
            cur.next = node.next;
        }
        size--;
        return node.value;
    }

    @Override
    public boolean containsKey(K key) {
        return find(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashIterator();
    }

    private static class Node<K,V> implements MyDictionary.Entry<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }
    }

    class HashIterator implements Iterator<Entry<K, V>> {
        int curBin = 0;
        Node<K, V> curNode = bins[curBin];
        int count = 0;

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) throw new NoSuchElementException();
            while (curNode == null) {
                curNode = bins[++curBin];
            }
            var res = curNode;
            curNode = curNode.next;
            count++;
            return res;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }
    }
}
