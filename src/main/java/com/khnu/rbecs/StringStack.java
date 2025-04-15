package com.khnu.rbecs;

public interface StringStack extends HasSize {
    void push(String el);
    String pop();
    String peekLast();
}
