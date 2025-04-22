package com.khnu.rbecs;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StringDoublyLinkedListTest {

    @Test
    void peekFirst() {
        StringDeque sa = new StringDoublyLinkedList();
        sa.enqueueLast("a");
        sa.enqueueLast("b");

        assertEquals("a", sa.peekFirst());
        assertEquals(2, sa.size());
        assertEquals("a", sa.dequeue());
        assertEquals(1, sa.size());
        assertEquals("b", sa.dequeue());
        assertEquals(0, sa.size());
        assertAll(
                () -> assertThrows(NoSuchElementException.class, () -> {
                    sa.peekFirst();
                }),
                () -> assertThrows(NoSuchElementException.class, () -> {
                    sa.peekLast();
                })
        );
    }

    @Test
    void size() {
        StringDeque sa = new StringDoublyLinkedList();
        sa.enqueueLast("a");
        sa.enqueueLast("b");
        sa.enqueueLast("c");
        sa.enqueueFirst("d");
        sa.enqueueFirst("e");
        sa.enqueueFirst("f");

        sa.dequeueLast();
        sa.dequeueFirst();

        assertEquals(4, sa.size());
    }

    @Test
    void iterator() {
        StringDeque sa = new StringDoublyLinkedList();
        sa.enqueueLast("a");
        sa.enqueueLast("b");
        sa.enqueueLast("c");
        sa.enqueueFirst("d");
        sa.enqueueFirst("e");
        sa.enqueueFirst("f");
        StringBuilder res = new StringBuilder();
        String expected = "fedabc";

        StringIterator it = sa.iterator();
        while (it.hasNext()) {
            String el = it.next();
            res.append(el);
        }

        assertEquals(expected, res.toString());
    }

    @Test
    void reverseIterator() {
        StringDeque sa = new StringDoublyLinkedList();
        sa.enqueueLast("a");
        sa.enqueueLast("b");
        sa.enqueueLast("c");
        sa.enqueueFirst("d");
        sa.enqueueFirst("e");
        sa.enqueueFirst("f");
        StringBuilder res = new StringBuilder();
        String expected = "cbadef";

        StringIterator it = sa.reverseIterator();
        while (it.hasNext()) {
            String el = it.next();
            res.append(el);
        }

        assertEquals(expected, res.toString());
    }
}