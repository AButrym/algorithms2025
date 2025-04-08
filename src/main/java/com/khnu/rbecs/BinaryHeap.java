package com.khnu.rbecs;

import java.util.NoSuchElementException;

public class BinaryHeap implements MyPriorityQueue {
    private Node root = null;
    private int size = 0;

    private static class Node {
        int value;
        Node left;
        Node right;
        Node parent;
        Node(int value) {
            this.value = value;
        }
    }


    @Override
    public void add(int el) {
        var node = new Node(el);
        addLast(node);
        while (promote(node)) {}
        size++;
    }

    boolean promote(Node node) {
        Node parent = node.parent;
        if (node.value < parent.value) {
            swap(node, parent);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int peekMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return root.value;
    }

    @Override
    public int pollMin() {
        int res = peekMin();
        Node last = getLast();
        swap(root, last); // TODO
        sink(root);
        size--;
        return res;
    }

    void sink(Node node) {
        Node best = node.left;
        if (node.right != null && node.right.value < node.left.value) {
            best = node.right;
        }
        if (best != null && best.value < node.value) {
            swap(node, best);
            sink(node);
        }
    }
}
