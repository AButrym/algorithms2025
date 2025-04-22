package com.khnu.rbecs;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Stack;

public class StringArray implements StringStack {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float GROW_FACTOR = 1.5f;
    // 10 20 40 80 160 320 640 == 640 = N
    // 10 30 70 150 310 630 1270 == 2*640 - 10 = O(N)
    // амортизований час = O(1)
    private String[] array = new String[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(String el) {
        ensureCapacity();
        array[size++] = el;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            int newSize = (int) (GROW_FACTOR * array.length);
            String[] newArray = new String[newSize];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Override
    public String pop() {
        checkNotEmpty();
        size--;
        var res = array[size];
        array[size] = null; // prevents memory leak
        return res;
    }

    @Override
    public String peekLast() {
        checkNotEmpty();
        return array[size - 1];
    }

    @Override
    public StringIterator iterator() {
        return new StringArrayIterator();
    }

    private class StringArrayIterator implements StringIterator {
        int cursor = size;
        @Override
        public boolean hasNext() {
            return cursor > 0;
        }
        @Override
        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            return array[--cursor];
        }
    }
}
