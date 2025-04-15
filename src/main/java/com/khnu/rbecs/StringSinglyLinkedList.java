package com.khnu.rbecs;

import java.util.NoSuchElementException;

public class StringSinglyLinkedList implements
        StringStack, StringQueue
{
    // Node -> Node -> Node -> Node -> Node -> null
    // HEAD                            TAIL

    private static class Node {
        String data;
        Node next;
        public Node(String data) {
            this.data = data;
        }
    }

    private int size = 0;
    private Node head = null;
    private Node tail = null;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(String s) {
        if (tail == null) {
            head = new Node(s);
            tail = head;
        } else {
            var node = new Node(s);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public String dequeue() {
        return pop();
    }

    @Override
    public String peekFirst() {
        return peekLast();
    }

    @Override
    public void push(String el) {
        var node = new Node(el);
        node.next = head;
        head = node;
        size++;
    }

    @Override
    public String pop() {
        checkNotEmpty();
        var res = head.data;
        head = head.next;
        size--;
        if (size == 0) tail = null;
        return res;
    }

    private void checkNotEmpty() {
        if (size == 0) throw new NoSuchElementException();
    }

    @Override
    public String peekLast() {
        checkNotEmpty();
        return head.data;
    }
}
