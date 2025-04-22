package com.khnu.rbecs;

public interface StringStack
        extends HasSize, StringIterable
{
    void push(String el);
    String pop();
    String peekLast();
}
