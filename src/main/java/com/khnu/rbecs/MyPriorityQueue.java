package com.khnu.rbecs;

public interface MyPriorityQueue {
    void add(int el);
    int size();
    int peekMin();
    int pollMin();

    default boolean isEmpty() {
        return size() == 0;
    }
}
