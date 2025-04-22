package com.khnu.rbecs;

public interface StringQueue
        extends HasSize, StringIterable
{
    void enqueue(String s);
    String dequeue();
    String peekFirst();
}
