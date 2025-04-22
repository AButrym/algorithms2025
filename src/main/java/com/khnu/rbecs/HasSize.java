package com.khnu.rbecs;

import java.util.NoSuchElementException;

public interface HasSize {
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
    default boolean isNotEmpty() {
        return size() > 0;
    }
    default void checkNotEmpty() {
        if (size() == 0) throw new NoSuchElementException();
    }
}
