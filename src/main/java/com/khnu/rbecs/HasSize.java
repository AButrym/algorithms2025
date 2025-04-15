package com.khnu.rbecs;

public interface HasSize {
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
    default boolean isNotEmpty() {
        return size() > 0;
    }
}
