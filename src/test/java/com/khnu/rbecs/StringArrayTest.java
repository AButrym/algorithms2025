package com.khnu.rbecs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringArrayTest {

    @Test
    void test1() {
        StringArray sa = new StringArray();

        sa.push("a");

        assertEquals("a", sa.peekLast());
        assertEquals(1, sa.size());
        assertEquals("a", sa.pop());
        assertEquals(0, sa.size());
    }

    @Test
    void test2() {
        StringArray sa = new StringArray();
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
    void test3() {
        StringArray sa = new StringArray();
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
}