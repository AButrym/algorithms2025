package com.khnu.rbecs;

import java.util.NoSuchElementException;

public class StringDoublyLinkedList implements StringDeque {
    private int size = 0;
    private final Node head = new Node(null);
    {
        head.next = head;
        head.prev = head;
    }

    // -> HEAD -> Node -> Node -> loopToHead
    // <-      <-      <-      <-
    //    <- newNode ->

    private static class Node {
        String data;
        Node next;
        Node prev;
        public Node(String data) {
            this.data = data;
        }
    }

    @Override
    public void enqueueFirst(String s) {
        var node = new Node(s);
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
        size++;
    }

    @Override
    public String dequeueLast() {
        String res = peekLast();
        Node secondToLast = head.prev.prev;
        head.prev = secondToLast;
        secondToLast.next = head;
        size--;
        return res;
    }
    @Override
    public String dequeue() {
        String res = peekFirst();
        Node second = head.next.next;
        head.next = second;
        second.prev = head;
        size--;
        return res;
    }

    @Override
    public String peekLast() {
        checkNotEmpty();
        return head.prev.data;
    }

    @Override
    public void enqueue(String s) {
        var node = new Node(s);
        node.prev = head.prev;
        node.prev.next = node;
        node.next = head;
        head.prev = node;
        size++;
    }

    @Override
    public String peekFirst() {
        checkNotEmpty();
        return head.next.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public StringIterator iterator() {
        return new StringIteratorImpl();
    }

    @Override
    public StringIterator reverseIterator() {
        return new StringReverseIteratorImpl();
    }

    private class StringReverseIteratorImpl implements StringIterator {
        Node cursor = head;

        @Override
        public boolean hasNext() {
            return cursor.prev != head;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            cursor = cursor.prev;
            return cursor.data;
        }
    }

    private class StringIteratorImpl implements StringIterator {
        Node cursor = head;

        @Override
        public boolean hasNext() {
            return cursor.next != head;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            cursor = cursor.next;
            return cursor.data;
        }
    }
}
