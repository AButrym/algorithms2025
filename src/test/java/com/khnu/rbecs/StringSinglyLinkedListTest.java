package com.khnu.rbecs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSinglyLinkedListTest {
    @Test
    void test1() {
        StringStack sa = new StringSinglyLinkedList();

        sa.push("a");

        assertEquals("a", sa.peekLast());
        assertEquals(1, sa.size());
        assertEquals("a", sa.pop());
        assertEquals(0, sa.size());
    }

    @Test
    void test2() {
        StringStack sa = new StringSinglyLinkedList();
        sa.push("a");
        sa.push("b");
        sa.push("c");
        StringBuilder res = new StringBuilder();
        String expected = "cba";

        while (sa.isNotEmpty()) {
            res.append(sa.pop());
        }

        assertEquals(expected, res.toString());
    }

    @Test
    void testStackIterator1() {
        StringStack sa = new StringSinglyLinkedList();
        sa.push("a");
        sa.push("b");
        sa.push("c");
        StringBuilder res = new StringBuilder();
        String expected = "cba";

        StringIterator it = sa.iterator();
        while (it.hasNext()) {
            String el = it.next();
            res.append(el);
        }

        assertEquals(expected, res.toString());
        assertEquals(3, sa.size());
    }
    @Test
    void testStackIterator2() {
        StringStack sa = new StringArray();
        sa.push("a");
        sa.push("b");
        sa.push("c");
        StringBuilder res = new StringBuilder();
        String expected = "cba";

        StringIterator it = sa.iterator();
        while (it.hasNext()) {
            String el = it.next();
            res.append(el);
        }

        assertEquals(expected, res.toString());
        assertEquals(3, sa.size());
    }

    @Test
    void testQueueIterator1() {
        StringQueue sa = new StringSinglyLinkedList();
        sa.enqueue("a");
        sa.enqueue("b");
        sa.enqueue("c");
        StringBuilder res = new StringBuilder();
        String expected = "abc";

        StringIterator it = sa.iterator();
        while (it.hasNext()) {
            String el = it.next();
            res.append(el);
        }

        assertEquals(expected, res.toString());
        assertEquals(3, sa.size());
    }

    @Test
    void test3() {
        // Dependency Inversion
        StringStack sa = new StringSinglyLinkedList();
        for (int i = 0; i < 16; i++) {
            sa.push(String.valueOf(i));
        }
        StringBuilder res = new StringBuilder();
        String expected = "1514131211109876543210";

        while (sa.isNotEmpty()) {
            res.append(sa.pop());
        }

        assertEquals(expected, res.toString());
    }

    @Test
    void test11() {
        StringQueue sa = new StringSinglyLinkedList();

        sa.enqueue("a");

        assertEquals("a", sa.peekFirst());
        assertEquals(1, sa.size());
        assertEquals("a", sa.dequeue());
        assertEquals(0, sa.size());
    }

    @Test
    void test21() {
        StringQueue sa = new StringSinglyLinkedList();
        sa.enqueue("a");
        sa.enqueue("b");
        sa.enqueue("c");
        StringBuilder res = new StringBuilder();
        String expected = "abc";

        while (sa.isNotEmpty()) {
            res.append(sa.dequeue());
        }

        assertEquals(expected, res.toString());
    }

    @Test
    void test31() {
        // Dependency Inversion
        StringQueue sa = new StringSinglyLinkedList();
        for (int i = 0; i < 16; i++) {
            sa.enqueue(String.valueOf(i));
        }
        StringBuilder res = new StringBuilder();
        String expected = "0123456789101112131415";

        while (sa.isNotEmpty()) {
            res.append(sa.dequeue());
        }

        assertEquals(expected, res.toString());
    }
}