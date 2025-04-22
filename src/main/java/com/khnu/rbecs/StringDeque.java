package com.khnu.rbecs;

public interface StringDeque extends StringQueue {
    // Queue + Deck = Deque (DoubleEndedQueue)
    void enqueueFirst(String s);
    String dequeueLast();
    String peekLast();

    default void enqueueLast(String s) {
        enqueue(s);
    }
    default String dequeueFirst() {
        return dequeue();
    }

    StringIterator reverseIterator();
}
