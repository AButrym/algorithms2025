package com.khnu.rbecs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashTable implements MyDictionary {
    public static final float LOAD_FACTOR = 0.75f;
//    public static final float RESIZE_FACTOR = 2.0f;
    private static final int DEFAULT_CAPACITY = 8;
    private int mask = 0b111;
    private Node[] bins = new Node[DEFAULT_CAPACITY];
    private int size = 0;

    private int ixBin(String key) {
        return key.hashCode() & mask;
//        return Math.abs(key.hashCode()) % bins.length;
    }

    @Override
    public String put(String key, String value) {
        Node node = find(key);
        if (node != null) {
            String oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        Node newNode = new Node(key, value);
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
        Node[] newBins = new Node[newCapacity];
        for (Entry entry : this) {
            Node node = (Node) entry;
            int ixBin = ixBin(node.key);
            node.next = newBins[ixBin];
            newBins[ixBin] = node;
        }
        bins = newBins;
    }

    private Node find(String key) {
        int ixBin = ixBin(key);
        Node node = bins[ixBin];
        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public String get(String key) {
        Node node = find(key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    @Override
    public String remove(String key) {
        Node node = find(key);
        if (node == null) return null;
        int ixBin = ixBin(key);
        Node cur = bins[ixBin];
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
    public boolean containsKey(String key) {
        return find(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry> iterator() {
        return new HashIterator();
    }

    private static class Node implements MyDictionary.Entry {
        String key;
        String value;
        Node next;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public void setValue(String value) {
            this.value = value;
        }
    }

    class HashIterator implements Iterator<Entry> {
        int curBin = 0;
        Node curNode = bins[curBin];
        int count = 0;

        @Override
        public Entry next() {
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
