package com.khnu.rbecs;

public interface StringQueue extends HasSize {
    void enqueue(String s);
    String dequeue();
    String peekFirst();
}
